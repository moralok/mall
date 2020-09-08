package com.moralok.mall.domain.dto;

import com.moralok.mall.domain.entity.OmsCartItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author moralok
 * @since 2020/9/8 3:08 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CartItemDto extends OmsCartItem {

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 价格
     */
    private BigDecimal price;
}
