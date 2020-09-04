package com.moralok.mall.domain.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询商品列表的条件参数
 *
 * @author moralok
 * @since 2020/9/4 2:51 下午
 */
@Data
@ApiModel(value = "查询商品列表的条件参数")
public class QueryProductCondition {

    /**
     * 品牌ID
     */
    @ApiModelProperty(value = "品牌ID")
    Integer brandId;
}
