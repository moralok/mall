package com.moralok.mall.controller;


import com.moralok.mall.domain.entity.UmsDeliveryAddress;
import com.moralok.mall.service.IUmsDeliveryAddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户收货地址表 前端控制器
 * </p>
 *
 * @author moralok
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/umsDeliveryAddress")
public class UmsDeliveryAddressController {

    @Autowired
    private IUmsDeliveryAddressService deliveryAddressService;

    @ApiOperation("添加收货地址")
    @PostMapping("")
    public void add(@RequestBody UmsDeliveryAddress address) {
        deliveryAddressService.add(address);
    }

    @ApiOperation("查询收货地址列表")
    @GetMapping("/list")
    public List<UmsDeliveryAddress> list() {
        return deliveryAddressService.listByUserId();
    }

    @ApiOperation("查询指定ID收货地址")
    @GetMapping("/{id}")
    public UmsDeliveryAddress getById(@ApiParam("地址ID") @PathVariable Integer id) {
        return deliveryAddressService.getByIdAndUserId(id);
    }

    @ApiOperation("更新指定ID收货地址")
    @PostMapping("/{id}")
    public void updateById(@ApiParam("地址ID") @PathVariable Integer id,
                                         @RequestBody UmsDeliveryAddress address) {
        deliveryAddressService.updateByIdAndUserId(id, address);
    }

    @ApiOperation("删除指定ID收货地址")
    @DeleteMapping("/{id}")
    public void updateById(@ApiParam("地址ID") @PathVariable Integer id) {
        deliveryAddressService.deleteByIdAndUserId(id);
    }
}

