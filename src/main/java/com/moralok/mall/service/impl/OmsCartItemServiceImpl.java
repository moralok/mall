package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.moralok.mall.domain.dto.CartItemDto;
import com.moralok.mall.domain.entity.OmsCartItem;
import com.moralok.mall.dao.OmsCartItemMapper;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IOmsCartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moralok.mall.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-09-08
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements IOmsCartItemService {

    @Autowired
    private IUmsUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(OmsCartItem cartItem) {
        // 是否需要参数合法性校验呢
        UmsUser user = userService.getCurrentUser();
        cartItem.setUserId(user.getId());
        OmsCartItem existCartItem = getCartItem(cartItem);
        // 存在并发问题
        if (existCartItem == null) {
            return save(cartItem);
        } else {
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            return updateById(existCartItem);
        }
    }

    @Override
    public List<CartItemDto> listByUserId(Integer userId) {
        return this.baseMapper.listByUserId(userId);
    }

    @Override
    public boolean updateQuantity(Integer id, Integer quantity) {
        LambdaUpdateWrapper<OmsCartItem> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(OmsCartItem::getQuantity, quantity).eq(OmsCartItem::getId, id);
        return update(updateWrapper);
    }

    @Override
    public boolean delete(Integer userId, List<Integer> ids) {
        LambdaQueryWrapper<OmsCartItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OmsCartItem::getUserId, userId).in(OmsCartItem::getId, ids);
        return remove(queryWrapper);
    }

    @Override
    public boolean clear(Integer userId) {
        LambdaQueryWrapper<OmsCartItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OmsCartItem::getUserId, userId);
        return remove(queryWrapper);
    }

    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        LambdaQueryWrapper<OmsCartItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OmsCartItem::getProductId, cartItem.getProductId())
                .eq(OmsCartItem::getProductSkuId, cartItem.getProductSkuId())
                .eq(OmsCartItem::getUserId, cartItem.getUserId())
                .orderByDesc(OmsCartItem::getId)
                .last("limit 1");
        return getOne(queryWrapper);
    }
}
