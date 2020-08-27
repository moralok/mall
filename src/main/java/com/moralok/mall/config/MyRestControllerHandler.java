package com.moralok.mall.config;

import com.moralok.mall.domain.CommonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author moralok
 * @since 2020/8/27 上午10:48
 */
@RestControllerAdvice
public class MyRestControllerHandler {

    @ExceptionHandler(value = {Exception.class})
    public CommonResult handleException(Exception e) {
        // 会优先匹配更具体的异常
        CommonResult commonResult = CommonResult.failed("系统走神了");
        e.printStackTrace();
        return commonResult;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 使用 @Valid 或 @Validated 抛出的异常
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String field = fieldError.getField();
            Object value = fieldError.getRejectedValue();
            String msg = fieldError.getDefaultMessage();
            String message = String.format("错误字段：%s，错误值：%s，原因：%s；", field, value, msg);
            sb.append(message);
        }
        return CommonResult.failed(sb.toString());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public CommonResult handleIllegalArgumentException(IllegalArgumentException e) {
        // 参数错误抛出的异常
        CommonResult commonResult = CommonResult.failed(e.getMessage());
        e.printStackTrace();
        return commonResult;
    }

    @ExceptionHandler(value = {ShiroException.class})
    public CommonResult handleShiroException(ShiroException e) {
        // 参数错误抛出的异常
        CommonResult commonResult;
        if (e instanceof AuthenticationException) {
            // 返回模糊的验证错误信息
            commonResult = CommonResult.failed("请登录");
        } else if (e instanceof AuthorizationException) {
            // 返回具体的错误信息
            commonResult = CommonResult.failed(e.getMessage());
        } else {
            commonResult = CommonResult.failed("账号存在问题");
        }
        return commonResult;
    }
}
