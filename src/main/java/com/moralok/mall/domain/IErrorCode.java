package com.moralok.mall.domain;

/**
 * @author moralok
 * @since 2020/8/18 下午2:05
 */
public interface IErrorCode {

    /**
     * 返回错误码
     *
     * @return
     */
    int getCode();

    /**
     * 返回提示消息
     *
     * @return
     */
    String getMessage();
}
