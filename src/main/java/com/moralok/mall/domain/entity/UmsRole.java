package com.moralok.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsRole extends Model<UmsRole> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String note;

    /**
     * 状态 0-禁用 1-正常
     */
    private Boolean status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
