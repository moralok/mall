package com.moralok.mall.service.impl;

import com.moralok.mall.domain.entity.UmsRole;
import com.moralok.mall.dao.UmsRoleMapper;
import com.moralok.mall.service.IUmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author moralok
 * @since 2020-08-20
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

    @Autowired
    private UmsRoleMapper umsRoleMapper;

    @Override
    public List<UmsRole> getRoles(Integer userId) {
        return umsRoleMapper.getRoles(userId);
    }
}
