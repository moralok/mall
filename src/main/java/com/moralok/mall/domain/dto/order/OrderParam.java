package com.moralok.mall.domain.dto.order;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author moralok
 * @since 2020/8/18 下午5:21
 */
@Data
public class OrderParam {

    /**
     * 商品sku ID
     */
    @NotNull(message = "商品sku ID不能为空")
    private Integer productSkuId;

    /**
     * 商品购买数量
     */
    @NotNull(message = "商品数量不能为空")
    @Min(value = 1, message = "商品购买数量>=1")
    private Integer productQuantity;

}
