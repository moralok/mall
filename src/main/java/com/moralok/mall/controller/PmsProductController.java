package com.moralok.mall.controller;


import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.EsProduct;
import com.moralok.mall.domain.entity.PmsProduct;
import com.moralok.mall.service.IPmsProductService;
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
public class PmsProductController {

    @Autowired
    private IPmsProductService pmsProductService;

    @GetMapping("/listAll")
    public CommonResult listAll() {
        List<PmsProduct> productList = pmsProductService.list();
        return CommonResult.success(productList);
    }

    @GetMapping("/search")
    public CommonResult searchByName(@RequestParam String name) {
        List<EsProduct> productList = pmsProductService.searchByName(name);
        return CommonResult.success(productList);
    }

    @PostMapping("/saveAllToEs")
    public CommonResult saveAllToEs() {
        pmsProductService.saveAllToEs();
        return CommonResult.success();
    }
}

