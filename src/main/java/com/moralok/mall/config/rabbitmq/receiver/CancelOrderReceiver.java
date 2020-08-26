package com.moralok.mall.config.rabbitmq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author moralok
 * @since 2020/8/26 下午5:40
 */
@Component
@Slf4j
public class CancelOrderReceiver {

    @RabbitListener(queues = "mall.order.cancel")
    public void handle(Integer orderId) {
        log.info("process orderId:{}", orderId);
    }
}
