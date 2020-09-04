package com.moralok.mall.service;

import com.moralok.mall.domain.entity.PmsSkuStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品sku表 服务类
 * </p>
 *
 * @author moralok
 * @since 2020-08-18
 */
public interface IPmsSkuStockService extends IService<PmsSkuStock> {

    /**
     * 根据商品ID查询sku列表
     *
     * @param productId
     * @return
     */
    List<PmsSkuStock> listByProductId(Integer productId);
}
