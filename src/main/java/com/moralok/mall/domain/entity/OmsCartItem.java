package com.moralok.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author moralok
 * @since 2020-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("购物车")
public class OmsCartItem extends Model<OmsCartItem> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer id;

    /**
     * 商品ID
     */
    @ApiModelProperty("商品ID")
    @NotNull(message = "商品ID为空")
    private Integer productId;

    /**
     * 商品skuID
     */
    @ApiModelProperty("商品skuID")
    @NotNull(message = "商品skuID为空")
    private Integer productSkuId;

    /**
     * 用户ID
     */
    @ApiModelProperty(hidden = true)
    private Integer userId;

    /**
     * 购买数量
     */
    @ApiModelProperty("购买数量")
    @NotNull(message = "请选择购买数量")
    @Min(value = 1, message = "购买数量至少为1")
    private Integer quantity;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
