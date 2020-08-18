package com.moralok.mall.service;

import com.moralok.mall.domain.entity.UmsUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author moralok
 * @since 2020-08-17
 */
public interface IUmsUserService extends IService<UmsUser> {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    UmsUser getByUsername(String username);

    /**
     * 查询当前的登录用户
     *
     * @return
     */
    UmsUser getCurrentUser();
}
