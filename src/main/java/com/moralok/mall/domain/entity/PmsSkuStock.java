package com.moralok.mall.domain.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品sku表
 * </p>
 *
 * @author moralok
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsSkuStock extends Model<PmsSkuStock> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 市场价
     */
    private BigDecimal listPrice;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 活动价
     */
    private BigDecimal activityPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 低库存预警
     */
    private Integer lowStock;

    /**
     * 已售
     */
    private Integer sold;

    /**
     * sku 图片
     */
    private String skuPic;

    /**
     * 乐观锁版本号
     */
    @Version
    private Integer version;

    /**
     * 逻辑删除
     */
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
