package com.moralok.mall.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author moralok
 * @since 2020/9/9 9:43 上午
 */
@WebFilter(urlPatterns = "/test/*")
@Slf4j
public class DogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("DogFilter doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DogFilter init");
    }

    @Override
    public void destroy() {
        log.info("DogFilter destroy");
    }
}
