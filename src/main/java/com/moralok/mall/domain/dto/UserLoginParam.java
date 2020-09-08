package com.moralok.mall.domain.dto;

import com.moralok.mall.validation.constraints.Age;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author moralok
 * @since 2020/8/18 上午11:49
 */
@Data
public class UserLoginParam {

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @Length(min = 1, max = 10, message = "用户名长度1-10")
    private String username;

    /**
     * 密码
     */
    @NotNull
    @Length(min = 1, max = 10, message = "密码长度1-10")
    private String password;

    /**
     * 仅仅为了测试Bean参数级联校验功能
     */
    private List<@Valid @Length(min = 1, max = 10) String> justForValidation;

    /**
     * 仅仅为了测试Bean参数自定义校验
     */
    @Age(max = 100)
    private Integer age;
}
