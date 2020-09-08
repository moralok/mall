package com.moralok.mall.dao;

import com.moralok.mall.domain.dto.CartItemDto;
import com.moralok.mall.domain.entity.OmsCartItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author moralok
 * @since 2020-09-08
 */
public interface OmsCartItemMapper extends BaseMapper<OmsCartItem> {

    /**
     * 根据用户ID查询购物车列表
     *
     * @param userId 用户ID
     * @return 购物车列表
     */
    List<CartItemDto> listByUserId(@Param("userId") Integer userId);
}
