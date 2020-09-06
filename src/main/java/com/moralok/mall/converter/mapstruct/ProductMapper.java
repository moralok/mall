package com.moralok.mall.converter.mapstruct;

import com.moralok.mall.domain.dto.EsProduct;
import com.moralok.mall.domain.entity.PmsProduct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author moralok
 * @since 2020/9/6
 */
@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * PmsProduct to EsProduct
     * @param product
     * @return
     */
    EsProduct productToEsProduct(PmsProduct product);
}
