package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moralok.mall.dao.OmsOrderMapper;
import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.order.OrderParam;
import com.moralok.mall.domain.entity.OmsOrder;
import com.moralok.mall.domain.entity.OmsOrderItem;
import com.moralok.mall.domain.entity.PmsSkuStock;
import com.moralok.mall.service.IOmsOrderItemService;
import com.moralok.mall.service.IOmsOrderService;
import com.moralok.mall.service.IPmsSkuStockService;
import com.moralok.mall.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private IPmsSkuStockService pmsSkuStockService;

    @Autowired
    private IUmsUserService umsUserService;

    @Autowired
    private IOmsOrderItemService omsOrderItemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult generateOrder(OrderParam orderParam) {
        PmsSkuStock skuStock = pmsSkuStockService.getById(orderParam.getProductSkuId());
        if (!hasStock(skuStock, orderParam.getProductQuantity())) {
            return CommonResult.failed("库存不足，下单失败");
        }
        if (!lockStock(skuStock, orderParam.getProductQuantity())) {
            return CommonResult.failed("系统繁忙，请稍后再试");
        }
        OmsOrderItem orderItem = new OmsOrderItem();
        orderItem.setProductId(skuStock.getProductId());
        orderItem.setProductPrice(skuStock.getPrice());
        orderItem.setProductSkuId(orderParam.getProductSkuId());
        orderItem.setProductQuantity(orderParam.getProductQuantity());
        OmsOrder order = new OmsOrder();
        order.setUserId(umsUserService.getCurrentUser().getId());
        order.setPayAmount(skuStock.getPrice().multiply(BigDecimal.valueOf(orderParam.getProductQuantity())));
        order.setCreatedAt(LocalDateTime.now());
        save(order);
        orderItem.setOrderId(order.getId());
        omsOrderItemService.save(orderItem);
        return CommonResult.success(order, "下单成功");
    }

    private boolean hasStock(PmsSkuStock skuStock, Integer productQuantity) {
        return skuStock.getStock() >= productQuantity;
    }

    private boolean lockStock(PmsSkuStock skuStock, Integer productQuantity) {
        skuStock.setStock(skuStock.getStock() - productQuantity).setSold(skuStock.getSold() + productQuantity);
        return pmsSkuStockService.updateById(skuStock);
    }
}
