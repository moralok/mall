package com.moralok.mall.repository;

import com.moralok.mall.domain.dto.EsProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author moralok
 * @since 2020/8/26 下午3:33
 */
public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Integer> {

    /**
     * 根据商品名称搜索
     *
     * @param name
     * @return
     */
    List<EsProduct> findByNameLike(String name);
}
