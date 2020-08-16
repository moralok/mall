package com.moralok.mall.service.impl;

import com.moralok.mall.domain.entity.PmsProduct;
import com.moralok.mall.dao.PmsProductMapper;
import com.moralok.mall.service.IPmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
