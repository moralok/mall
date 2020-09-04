package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.moralok.mall.domain.entity.PmsSkuStock;
import com.moralok.mall.dao.PmsSkuStockMapper;
import com.moralok.mall.service.IPmsSkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品sku表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-08-18
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements IPmsSkuStockService {

    @Override
    public List<PmsSkuStock> listByProductId(Integer productId) {
        LambdaQueryWrapper<PmsSkuStock> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsSkuStock::getProductId, productId);
        return list(queryWrapper);
    }
}
