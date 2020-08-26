package com.moralok.mall.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
     * 商品名称
     */
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 是否在售
     */
    @Field(type = FieldType.Boolean)
    private Boolean onSale;
}
