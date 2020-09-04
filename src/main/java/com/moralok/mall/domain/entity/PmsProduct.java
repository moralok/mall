package com.moralok.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author moralok
 * @since 2020-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsProduct extends Model<PmsProduct> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌ID
     */
    private Integer brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品缩略图
     */
    private String thumbnail;

    /**
     * 图片
     */
    private String pics;

    /**
     * 是否在售
     */
    private Boolean onSale;

    /**
     * 逻辑删除
     */
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
