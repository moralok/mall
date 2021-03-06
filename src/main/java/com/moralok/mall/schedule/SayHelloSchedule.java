package com.moralok.mall.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author moralok
 * @since 2020/9/8 6:19 下午
 */
@Component
public class SayHelloSchedule {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void sayHelloBoy() {
        System.out.printf("Hello Boy %d %n", System.currentTimeMillis());
    }

    @Scheduled(cron = "30 0/1 * * * ?")
    public void sayHelloGirl() {
        System.out.printf("Hello Girl %d %n", System.currentTimeMillis());
    }
}
