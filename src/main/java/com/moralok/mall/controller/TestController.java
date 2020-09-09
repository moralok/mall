package com.moralok.mall.controller;

import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.Dummy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moralok
 * @since 2020/8/16 下午3:14
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/hello", produces = "application/json; charset=UTF-8")
    public String hello() {
        return "hi";
    }

    @GetMapping("/testConverter")
    public CommonResult testConverter(Dummy dummy) {
        return CommonResult.success(dummy);
    }
}
