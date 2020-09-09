package com.moralok.mall.controller;


import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.user.UserLoginParam;
import com.moralok.mall.domain.dto.user.UpdatePasswordParam;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IUmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
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
@Api
@RestController
@RequestMapping("/umsUser")
public class UmsUserController {

    @Autowired
    private IUmsUserService userService;

    @ApiOperation("获取验证码")
    @GetMapping("/getVerificationCode")
    public CommonResult getVerificationCode(@RequestParam String phoneNumber) {
        return userService.getVerificationCode(phoneNumber);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public CommonResult register(@Validated @RequestBody UmsUser user) {
        return userService.register(user);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public CommonResult doLogin(@Validated @RequestBody UserLoginParam userLoginParam) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userLoginParam.getUsername(), userLoginParam.getPassword());
        subject.login(token);
        return CommonResult.success(null, "登录成功");
    }

    @ApiOperation(value = "获取当前登录用户")
    @GetMapping("/currentUser")
    public UmsUser getCurrentUser() {
        return userService.getCurrentUser();
    }

    @ApiOperation(value = "更改密码")
    @PostMapping("/updatePassword")
    public CommonResult updatePassword(@Validated @RequestBody UpdatePasswordParam param) {
        return userService.updatePassword(param);
    }
}

