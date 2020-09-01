package com.moralok.mall.controller;


import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.UserLoginParam;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IUmsUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author moralok
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/umsUser")
public class UmsUserController {

    @Autowired
    private IUmsUserService umsUserService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult register(@Validated() @RequestBody UmsUser user) {
        return umsUserService.register(user);
    }

    @PostMapping("/login")
    public CommonResult doLogin(@Validated @RequestBody UserLoginParam userLoginParam) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userLoginParam.getUsername(), userLoginParam.getPassword());
        subject.login(token);
        return CommonResult.success(null, "登录成功");
    }

    @RequiresPermissions("foo:read")
    @RequiresRoles("李白")
    @GetMapping("/currentUser")
    public CommonResult getCurrentUser() {
        return CommonResult.success(umsUserService.getCurrentUser());
    }

    @GetMapping("/login")
    public String login() {
        return "请登录";
    }
}

