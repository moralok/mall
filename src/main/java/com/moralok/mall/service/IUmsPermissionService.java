package com.moralok.mall.service;

import com.moralok.mall.domain.entity.UmsPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
public interface IUmsPermissionService extends IService<UmsPermission> {

    /**
     * 根据用户ID查询权限
     *
     * @param userId
     * @return
     */
    List<UmsPermission> getPermissions(Integer userId);
}
