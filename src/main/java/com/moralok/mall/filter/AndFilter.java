package com.moralok.mall.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author moralok
 * @since 2020/9/9 9:40 上午
 */
@Slf4j
public class AndFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("AndFilter doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AndFilter init");
    }

    @Override
    public void destroy() {
        log.info("AndFilter destroy");
    }
}
