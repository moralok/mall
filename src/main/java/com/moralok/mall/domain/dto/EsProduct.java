package com.moralok.mall.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author moralok
 * @since 2020/8/26 下午5:12
 */
@Data
@Document(indexName = "es_product_test")
public class EsProduct {

    @Id
    private Integer id;

    /**
     * 品牌ID
     */
    @Field(type = FieldType.Integer)
    private Integer brandId;

    /**
     * 品牌名称
     */
    @Field(type = FieldType.Text)
    private String brandName;

    /**
     * 商品名称
     */
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 商品描述
     */
    @Field(type = FieldType.Text)
    private String description;

    /**
     * 商品缩略图
     */
    @Field(type = FieldType.Text)
    private String thumbnail;

    /**
     * 图片
     */
    @Field(type = FieldType.Text)
    private List<String> pics;

    /**
     * 是否在售
     */
    @Field(type = FieldType.Boolean)
    private Boolean onSale;

    /**
     * 逻辑删除
     */
    @Field(type = FieldType.Boolean)
    private Boolean deleted;
}
