package com.moralok.mall.service;

import com.moralok.mall.domain.entity.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
public interface IUmsRoleService extends IService<UmsRole> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId
     * @return
     */
    List<UmsRole> getRoles(Integer userId);
}
