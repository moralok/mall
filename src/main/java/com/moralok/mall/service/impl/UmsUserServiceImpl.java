package com.moralok.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moralok.mall.dao.UmsUserMapper;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IUmsUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

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
        return (UmsUser) subject.getPrincipal();
    }
}
