package com.moralok.mall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.EsProduct;
import com.moralok.mall.domain.dto.product.QueryProductCondition;
import com.moralok.mall.domain.entity.PmsProduct;
import com.moralok.mall.domain.entity.PmsSkuStock;
import com.moralok.mall.service.IPmsProductService;
import com.moralok.mall.service.IPmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author moralok
 * @since 2020-08-16
 */
@RestController
@RequestMapping("/pmsProduct")
@Api(tags = "商品前端控制器")
public class PmsProductController {

    @Autowired
    private IPmsProductService productService;

    @Autowired
    private IPmsSkuStockService skuStockService;

    @ApiOperation(value = "根据条件分页查询商品")
    @GetMapping("/list")
    public IPage<PmsProduct> list(QueryProductCondition condition,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size) {
        return productService.list(condition, page, size);
    }

    @ApiOperation(value = "查询商品详情")
    @GetMapping("/detail")
    public PmsProduct getDetailById(@RequestParam Integer productId) {
        return productService.getById(productId);
    }

    @ApiOperation(value = "查询商品sku列表")
    @GetMapping("/skuList")
    public List<PmsSkuStock> getSkuListById(@ApiParam(value = "商品ID") @RequestParam Integer productId) {
        return skuStockService.listByProductId(productId);
    }

    @GetMapping("/search")
    public List<EsProduct> searchByName(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @PostMapping("/saveAllToEs")
    public CommonResult saveAllToEs() {
        productService.saveAllToEs();
        return CommonResult.success();
    }
}

