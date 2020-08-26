package com.moralok.mall.service.impl;

import com.moralok.mall.domain.dto.EsProduct;
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
    public List<EsProduct> searchByName(String name) {
        return esProductRepository.findByNameLike(name);
    }

    @Override
    public void saveAllToEs() {
        List<EsProduct> list = list().stream().map(pmsProduct -> {
            EsProduct esProduct = new EsProduct();
            BeanUtils.copyProperties(pmsProduct, esProduct);
            return esProduct;
        }).collect(Collectors.toList());
        esProductRepository.saveAll(list);
    }
}
