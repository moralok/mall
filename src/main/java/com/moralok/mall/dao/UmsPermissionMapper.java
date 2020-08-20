package com.moralok.mall.dao;

import com.moralok.mall.domain.entity.UmsPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    /**
     * 根据用户ID查询权限
     *
     * @param userId
     * @return
     */
    List<UmsPermission> getPermissions(@Param("userId") Integer userId);
}
