package com.moralok.mall.domain.constant;

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
    FAILED(500, "操作失败");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
