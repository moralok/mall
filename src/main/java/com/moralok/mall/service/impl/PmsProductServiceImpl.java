package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moralok.mall.converter.mapstruct.ProductMapper;
import com.moralok.mall.domain.dto.EsProduct;
import com.moralok.mall.domain.dto.product.QueryProductCondition;
import com.moralok.mall.domain.entity.PmsProduct;
import com.moralok.mall.dao.PmsProductMapper;
import com.moralok.mall.repository.EsProductRepository;
import com.moralok.mall.service.IPmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-08-16
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public IPage<PmsProduct> list(QueryProductCondition condition, Integer page, Integer size) {
        Page<PmsProduct> pageable = new Page<>(page, size);
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        if (condition.getBrandId() != null) {
            queryWrapper.eq(PmsProduct::getBrandId, condition.getBrandId());
        }
        return page(pageable, queryWrapper);
    }

    @Override
    public List<EsProduct> searchByName(String name) {
        return esProductRepository.findByNameLike(name);
    }

    @Override
    public void saveAllToEs() {
        List<EsProduct> list = list().stream().map(ProductMapper.INSTANCE::productToEsProduct).collect(Collectors.toList());
        esProductRepository.saveAll(list);
    }
}
