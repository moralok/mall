package com.moralok.mall.controller;


import com.moralok.mall.domain.entity.PmsProduct;
import com.moralok.mall.service.IPmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    public List<PmsProduct> listAll() {
        return pmsProductService.list();
    }
}

