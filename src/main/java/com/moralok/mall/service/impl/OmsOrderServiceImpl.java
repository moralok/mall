package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moralok.mall.config.rabbitmq.sender.CancelOrderSender;
import com.moralok.mall.dao.OmsOrderMapper;
import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.constant.ResultCode;
import com.moralok.mall.domain.dto.CartItemDto;
import com.moralok.mall.domain.dto.order.OrderParam;
import com.moralok.mall.domain.entity.*;
import com.moralok.mall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-08-18
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements IOmsOrderService {

    @Autowired
    private IPmsSkuStockService skuStockService;

    @Autowired
    private IUmsUserService userService;

    @Autowired
    private IOmsOrderItemService orderItemService;

    @Autowired
    private IOmsCartItemService cartItemService;

    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult generateOrder(OrderParam orderParam) {
        PmsSkuStock skuStock = skuStockService.getById(orderParam.getProductSkuId());
        // 检查库存
        hasStock(skuStock, orderParam.getProductQuantity());
        // 扣减库存
        lockStock(skuStock, orderParam.getProductQuantity());
        OmsOrderItem orderItem = new OmsOrderItem();
        orderItem.setProductId(skuStock.getProductId());
        orderItem.setProductPrice(skuStock.getPrice());
        orderItem.setProductSkuId(orderParam.getProductSkuId());
        orderItem.setProductQuantity(orderParam.getProductQuantity());
        OmsOrder order = new OmsOrder();
        order.setUserId(userService.getCurrentUser().getId());
        order.setPayAmount(skuStock.getPrice().multiply(BigDecimal.valueOf(orderParam.getProductQuantity())));
        order.setCreatedAt(LocalDateTime.now());
        save(order);
        orderItem.setOrderId(order.getId());
        orderItemService.save(orderItem);
        return CommonResult.success(order, "下单成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult generateOrder() {
        UmsUser user = userService.getCurrentUser();
        List<CartItemDto> cartItemDtoList = cartItemService.listByUserId(user.getId());
        cartItemDtoList.removeIf(item -> item.getStock() == null);
        if (CollectionUtils.isEmpty(cartItemDtoList)) {
            return CommonResult.failed("购物车无可结算商品");
        }
        // 检查库存
        hasStock(cartItemDtoList);
        // 扣减库存
        lockStock(cartItemDtoList);
        List<OmsOrderItem> orderItemList = new ArrayList<>(cartItemDtoList.size());
        OmsOrder order = new OmsOrder();
        for (CartItemDto item : cartItemDtoList) {
            OmsOrderItem orderItem = new OmsOrderItem();
            orderItem.setProductId(item.getProductId());
            orderItem.setProductPrice(item.getPrice());
            orderItem.setProductSkuId(item.getProductSkuId());
            orderItem.setProductQuantity(item.getQuantity());
            orderItem.setOrderId(order.getId());
            orderItemList.add(orderItem);
        }
        order.setUserId(user.getId());
        order.setPayAmount(cartItemDtoList.stream().reduce(BigDecimal.ZERO, (x, y) -> x.add(y.getPrice().multiply(BigDecimal.valueOf(y.getQuantity()))), BigDecimal::add));
        order.setCreatedAt(LocalDateTime.now());
        save(order);
        for (OmsOrderItem item : orderItemList) {
            item.setOrderId(order.getId());
        }
        orderItemService.saveBatch(orderItemList);
        cartItemService.removeByIds(cartItemDtoList.stream().map(OmsCartItem::getId).collect(Collectors.toList()));
        return CommonResult.success(order, "下单成功");
    }

    @Override
    public void sendCancelOrderDelayMsg(Integer orderId) {
        cancelOrderSender.sendMessage(orderId, 10000);
    }

    private void hasStock(PmsSkuStock skuStock, Integer productQuantity) {
        if (skuStock.getStock() < productQuantity) {
            throw ResultCode.INSUFFICIENT_STOCK.generateException();
        }
    }

    private void hasStock(List<CartItemDto> cartItemDtoList) {
        for (CartItemDto item : cartItemDtoList) {
            if (item.getStock() < item.getQuantity()) {
                throw ResultCode.INSUFFICIENT_STOCK.generateException();
            }
        }
    }

    private void lockStock(PmsSkuStock skuStock, Integer productQuantity) {
        skuStock.setStock(skuStock.getStock() - productQuantity).setSold(skuStock.getSold() + productQuantity);
        boolean res = skuStockService.updateById(skuStock);
        if (!res) {
            throw ResultCode.SYSTEM_IS_BUSY.generateException();
        }
    }

    private void lockStock(List<CartItemDto> cartItemDtoList) {
        for (CartItemDto item : cartItemDtoList) {
            // todo: 乐观锁插件不能生效，先查后更新有点不爽
            LambdaUpdateWrapper<PmsSkuStock> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(PmsSkuStock::getId, item.getProductSkuId()).set(PmsSkuStock::getStock, item.getStock() - item.getQuantity());
            if (skuStockService.update(updateWrapper)) {
                throw ResultCode.SYSTEM_IS_BUSY.generateException();
            }
        }
    }
}
