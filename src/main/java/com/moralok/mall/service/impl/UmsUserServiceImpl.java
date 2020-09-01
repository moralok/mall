package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moralok.mall.dao.UmsUserMapper;
import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IUmsUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-08-17
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {

    @Override
    public UmsUser getByUsername(String username) {
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUser::getUsername, username).last("limit 1");
        return getOne(queryWrapper);
    }

    @Override
    public UmsUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        UmsUser user = (UmsUser) subject.getPrincipal();
        // 脱敏
        user.setPassword(null);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult register(UmsUser user) {
        // 检查用户信息是否已经注册
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsUser::getUsername, user.getUsername()).or().eq(UmsUser::getPhoneNumber, user.getPhoneNumber());
        List<UmsUser> existUsers = list(queryWrapper);
        if (!CollectionUtils.isEmpty(existUsers)) {
            return CommonResult.failed("用户名或手机号已注册");
        }
        // 储存用户信息
        user.setPassword(new SimpleHash("md5", user.getPassword(), user.getUsername(), 2).toString());
        user.setCreatedAt(LocalDateTime.now());
        save(user);
        return CommonResult.success(null, "注册成功");
    }
}
