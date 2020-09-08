package com.moralok.mall.controller;


import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.entity.OmsCartItem;
import com.moralok.mall.service.IOmsCartItemService;
import com.moralok.mall.service.IUmsUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author moralok
 * @since 2020-09-08
 */
@RestController
@RequestMapping("/omsCartItem")
@Api
public class OmsCartItemController {

    @Autowired
    private IOmsCartItemService cartItemService;

    @Autowired
    private IUmsUserService userService;

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public boolean add(@Validated @RequestBody OmsCartItem cartItem) {
        return cartItemService.add(cartItem);
    }

    @ApiOperation("获取用户的购物车列表")
    @GetMapping("/list")
    public List<OmsCartItem> list() {
        return cartItemService.listByUserId(userService.getCurrentUser().getId());
    }

    @ApiOperation("更新购物车中某个商品的数量")
    @PostMapping("/update/quantity")
    public CommonResult updateQuantity(@ApiParam("购物车ID") @RequestParam Integer id,
                                       @ApiParam("商品数量") @RequestParam Integer quantity) {
        boolean res = cartItemService.updateQuantity(id, quantity);
        return res ? CommonResult.success() : CommonResult.failed();
    }

    @ApiOperation("删除购物车中的商品")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Integer> ids) {
        boolean res = cartItemService.delete(userService.getCurrentUser().getId(), ids);
        return res ? CommonResult.success() : CommonResult.failed();
    }

    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public CommonResult clear() {
        boolean res = cartItemService.clear(userService.getCurrentUser().getId());
        return res ? CommonResult.success() : CommonResult.failed();
    }
}

