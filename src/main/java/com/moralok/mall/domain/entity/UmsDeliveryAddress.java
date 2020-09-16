package com.moralok.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户收货地址表
 * </p>
 *
 * @author moralok
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsDeliveryAddress extends Model<UmsDeliveryAddress> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 收货人姓名
     */
    @ApiModelProperty("收货人姓名")
    private String name;

    /**
     * 收货人手机号
     */
    @ApiModelProperty("收货人手机号")
    private String phoneNumber;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idNumber;

    /**
     * 是否默认
     */
    @ApiModelProperty("是否默认")
    private Boolean defaultStatus;

    /**
     * 省份/直辖市
     */
    @ApiModelProperty("省份/直辖市")
    private String province;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty("区")
    private String region;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    private String detailAddress;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
