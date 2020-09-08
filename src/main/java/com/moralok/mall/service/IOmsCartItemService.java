package com.moralok.mall.service;

import com.moralok.mall.domain.entity.OmsCartItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author moralok
 * @since 2020-09-08
 */
public interface IOmsCartItemService extends IService<OmsCartItem> {

    /**
     * 添加购物车
     *
     * @param cartItem 购物车
     * @return 结果，boolean
     */
    boolean add(OmsCartItem cartItem);

    /**
     * 根据用户ID查询购物车列表
     *
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<OmsCartItem> listByUserId(Integer userId);

    /**
     * 更新购物车中某商品数量
     *
     * @param id
     * @param quantity
     * @return
     */
    boolean updateQuantity(Integer id, Integer quantity);

    /**
     * 批量删除购物车中的商品
     *
     * @param userId
     * @param ids
     * @return
     */
    boolean delete(Integer userId, List<Integer> ids);

    /**
     * 清空购物车
     *
     * @param userId
     * @return
     */
    boolean clear(Integer userId);
}
