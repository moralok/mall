package com.moralok.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsRolePermission extends Model<UmsRolePermission> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer permissionId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
