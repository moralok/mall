package com.moralok.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单中包含的商品
 * </p>
 *
 * @author moralok
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OmsOrderItem extends Model<OmsOrderItem> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 购买商品数量
     */
    private Integer productQuantity;

    /**
     * 商品的sku ID
     */
    private Integer productSkuId;

    /**
     * 商品销售价格
     */
    private BigDecimal productPrice;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
