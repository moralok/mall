package com.moralok.mall.domain.dto.order;

import lombok.Data;

/**
 * @author maowenrou
 * @since 2020/8/18 下午5:21
 */
@Data
public class OrderParam {

    /**
     * 商品sku ID
     */
    private Integer productSkuId;

    /**
     * 商品购买数量
     */
    private Integer productQuantity;

}
