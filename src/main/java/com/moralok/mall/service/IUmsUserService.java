package com.moralok.mall.service;

import com.moralok.mall.domain.CommonResult;
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
     * 将依赖于 Shiro 具体的实现隐藏
     *
     * @return
     */
    UmsUser getCurrentUser();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    CommonResult register(UmsUser user);
}
