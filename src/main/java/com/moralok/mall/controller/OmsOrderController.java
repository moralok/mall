package com.moralok.mall.controller;


import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.order.OrderParam;
import com.moralok.mall.service.IOmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class OmsOrderController {

    @Autowired
    private IOmsOrderService omsOrderService;

    @PostMapping("/generateOrder")
    public CommonResult generateOrder(@RequestBody OrderParam orderParam) {
        return omsOrderService.generateOrder(orderParam);
    }

    @PostMapping("/sendCancelOrderDelayMsg")
    public CommonResult sendCancelOrderDelayMsg(@RequestParam Integer orderId) {
        omsOrderService.sendCancelOrderDelayMsg(orderId);
        return CommonResult.success();
    }
}

