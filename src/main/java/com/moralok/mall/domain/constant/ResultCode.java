package com.moralok.mall.domain.constant;

import com.moralok.mall.config.BaseException;
import com.moralok.mall.domain.IErrorCode;
import lombok.Getter;

/**
 * @author moralok
 * @since 2020/8/18 下午1:55
 */
@Getter
public enum ResultCode implements IErrorCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 操作失败
     */
    FAILED(500, "操作失败"),

    // 基础
    SYSTEM_IS_BUSY(1001, "系统繁忙，请稍后再试"),

    // 订单
    INSUFFICIENT_STOCK(2001, "库存不足"),

    ;

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException generateException() {
        return new BaseException(this);
    }
}
