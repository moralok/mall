package com.moralok.mall.controller;


import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.order.OrderParam;
import com.moralok.mall.service.IOmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author moralok
 * @since 2020-08-18
 */
@RestController
@RequestMapping("/omsOrder")
@Api
public class OmsOrderController {

    @Autowired
    private IOmsOrderService omsOrderService;


    @ApiOperation("快速下单")
    @PostMapping("/quicklyGenerateOrder")
    public CommonResult generateOrder(@Valid @RequestBody OrderParam orderParam) {
        return omsOrderService.generateOrder(orderParam);
    }

    @ApiOperation("根据购物车生成订单")
    @PostMapping("/generateOrder")
    public CommonResult generateOrder() {
        return omsOrderService.generateOrder();
    }

    @PostMapping("/sendCancelOrderDelayMsg")
    public CommonResult sendCancelOrderDelayMsg(@RequestParam Integer orderId) {
        omsOrderService.sendCancelOrderDelayMsg(orderId);
        return CommonResult.success();
    }
}

