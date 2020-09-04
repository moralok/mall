package com.moralok.mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moralok.mall.domain.dto.EsProduct;
import com.moralok.mall.domain.dto.product.QueryProductCondition;
import com.moralok.mall.domain.entity.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author moralok
 * @since 2020-08-16
 */
public interface IPmsProductService extends IService<PmsProduct> {

    /**
     * 根据条件查询商品并分页
     *
     * @param condition 条件参数
     * @param page 页码
     * @param size 每页大小
     * @return
     */
    IPage<PmsProduct> list(QueryProductCondition condition, Integer page, Integer size);

    /**
     * 按商品名称查询
     *
     * @param name
     * @return
     */
    List<EsProduct> searchByName(String name);

    /**
     * 将所有商品存入ES
     */
    void saveAllToEs();
}
