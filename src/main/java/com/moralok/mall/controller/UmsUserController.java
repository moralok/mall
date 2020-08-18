package com.moralok.mall.controller;


import com.moralok.mall.domain.CommonResult;
import com.moralok.mall.domain.dto.UserLoginParam;
import com.moralok.mall.domain.entity.UmsUser;
import com.moralok.mall.service.IUmsUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public String doLogin(@RequestBody UserLoginParam userLoginParam) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(userLoginParam.getUsername(), userLoginParam.getPassword());
        subject.login(token);
        return "登录成功";
    }

    @GetMapping("/myself")
    public CommonResult getMySelf() {
        Subject subject = SecurityUtils.getSubject();
        UmsUser user = (UmsUser) subject.getPrincipal();
        return CommonResult.success(user);
    }

    @GetMapping("/login")
    public String login() {
        return "请登录";
    }
}

