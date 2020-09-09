package com.moralok.mall.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author moralok
 * @since 2020/9/9 9:43 上午
 */
@Slf4j
public class BoyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("BoyFilter doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("BoyFilter init");
    }

    @Override
    public void destroy() {
        log.info("BoyFilter destroy");
    }
}
