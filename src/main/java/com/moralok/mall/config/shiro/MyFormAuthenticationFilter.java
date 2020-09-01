package com.moralok.mall.config.shiro;

import cn.hutool.json.JSONUtil;
import com.moralok.mall.domain.CommonResult;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author maowenrou
 * @since 2020/9/1 2:13 下午
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(JSONUtil.toJsonStr(CommonResult.failed("请登录")));
        out.flush();
        out.close();
        return false;
    }
}
