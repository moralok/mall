package com.moralok.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author moralok
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户", description = "用户模型")
public class UmsUser extends Model<UmsUser> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户ID")
    private Integer id;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号码长度为11")
    @Pattern(regexp = "(((13[0-9])|(14([57]))|(15([0-3]|[5-9]))|(17(0|3|[5-8]))|(18[0-9])|166|198|199|190|197|196|192|191)[0-9]{8})", message = "手机号格式不正确")
    private String phoneNumber;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    @Length(min = 1, max = 20, message = "用户名长度1-20")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", example = "我是昵称叽里呱啦")
    @Length(min = 1, max = 20, message = "昵称长度1-20")
    private String nickname;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    @Length(min = 6, message = "密码长度至少为6")
    private String password;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 状态 0-禁用 1-正常
     */
    @ApiModelProperty(hidden = true)
    private Boolean status;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    private LocalDateTime updatedAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
