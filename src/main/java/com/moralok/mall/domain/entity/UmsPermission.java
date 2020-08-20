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
 * 权限表
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmsPermission extends Model<UmsPermission> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限值
     */
    private String value;

    /**
     * 父权限ID
     */
    private Integer parentId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
