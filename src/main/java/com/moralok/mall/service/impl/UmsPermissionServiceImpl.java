package com.moralok.mall.service.impl;

import com.moralok.mall.domain.entity.UmsPermission;
import com.moralok.mall.dao.UmsPermissionMapper;
import com.moralok.mall.service.IUmsPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements IUmsPermissionService {

    @Autowired
    private UmsPermissionMapper umsPermissionMapper;

    @Override
    public List<UmsPermission> getPermissions(Integer userId) {
        return umsPermissionMapper.getPermissions(userId);
    }
}
