package com.moralok.mall.config.shiro;

import com.moralok.mall.domain.entity.UmsPermission;
import com.moralok.mall.domain.entity.UmsRole;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IUmsPermissionService;
import com.moralok.mall.service.IUmsRoleService;
import com.moralok.mall.service.IUmsUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author moralok
 * @since 2020/8/18 上午10:10
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUmsUserService umsUserService;

    @Autowired
    private IUmsRoleService umsRoleService;

    @Autowired
    private IUmsPermissionService umsPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UmsUser user = (UmsUser) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<UmsRole> roles = umsRoleService.getRoles(user.getId());
        List<UmsPermission> permissions = umsPermissionService.getPermissions(user.getId());
        simpleAuthorizationInfo.addRoles(roles.stream().map(UmsRole::getName).collect(Collectors.toList()));
        simpleAuthorizationInfo.addStringPermissions(permissions.stream().map(UmsPermission::getValue).collect(Collectors.toList()));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        UmsUser user = umsUserService.getByUsername(username);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
