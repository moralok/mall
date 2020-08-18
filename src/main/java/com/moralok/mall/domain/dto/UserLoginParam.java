package com.moralok.mall.domain.dto;

import lombok.Data;

/**
 * @author moralok
 * @since 2020/8/18 上午11:49
 */
@Data
public class UserLoginParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
