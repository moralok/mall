package com.moralok.mall.service;

import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.user.UpdatePasswordParam;
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
     * @param username 用户名
     * @return
     */
    UmsUser getByUsername(String username);

    /**
     * 根据手机号查询用户
     *
     * @param phoneNumber 手机号码
     * @return
     */
    UmsUser getByPhoneNumber(String phoneNumber);

    /**
     * 查询当前的登录用户
     * 将依赖于 Shiro 具体的实现隐藏
     *
     * @return 当前登录用户
     */
    UmsUser getCurrentUser();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    CommonResult register(UmsUser user);

    /**
     * 更新密码
     *
     * @param param 更新密码所需参数
     * @return
     */
    CommonResult updatePassword(UpdatePasswordParam param);

    /**
     * 获取验证码，并发送至手机号码
     *
     * @param phoneNumber 手机号码
     * @return
     */
    CommonResult getVerificationCode(String phoneNumber);
}
