package com.moralok.mall.service;

import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.order.OrderParam;
import com.moralok.mall.domain.entity.OmsOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author moralok
 * @since 2020-08-18
 */
public interface IOmsOrderService extends IService<OmsOrder> {

    /**
     * 生成订单
     * 对应快速下单的业务
     *
     * @param orderParam
     * @return
     */
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 发送取消订单延时消息
     *
     * @param orderId
     */
    void sendCancelOrderDelayMsg(Integer orderId);
}
