package com.moralok.mall.domain.dto.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author moralok
 * @since 2020/9/9 5:47 下午
 */
@Data
public class UpdatePasswordParam {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号码长度为11")
    @Pattern(regexp = "(((13[0-9])|(14([57]))|(15([0-3]|[5-9]))|(17(0|3|[5-8]))|(18[0-9])|166|198|199|190|197|196|192|191)[0-9]{8})", message = "手机号格式不正确")
    private String phoneNumber;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    @Length(min = 6, message = "密码长度至少为6")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不能为空")
    @Length(min = 6, max = 6, message = "验证码长度为6位")
    private String verificationCode;
}
