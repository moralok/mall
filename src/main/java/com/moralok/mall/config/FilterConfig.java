package com.moralok.mall.config;

import com.moralok.mall.filter.AndFilter;
import com.moralok.mall.filter.BoyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * 使用 ServletComponentScan 扫描，filter 顺序取决于 filterName
 * 使用 FilterRegistrationBean 手动注册
 *     如果使用 List<FilterRegistrationBean<Filter>> 返回，取决于 Filter Bean 注入的顺序，addUrlPatterns 将失去效果
 *     如果使用 FilterRegistrationBean<Filter> 返回，取决于 FilterRegistrationBean 注入的顺序，可以通过 order 指定顺序
 * 前者优先于后者，看起来后者的灵活性更强
 *
 * @author moralok
 * @since 2020/9/9 10:10 上午
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> registerAndFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(andFilter());
        registration.addUrlPatterns("/test/*");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<Filter> registerBoyFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(boyFilter());
        registration.addUrlPatterns("/test/*");
        registration.setOrder(1);
        // todo: 初步测试没有效果啊
        registration.setAsyncSupported(true);
        return registration;
    }

    @Bean
    public Filter andFilter() {
        return new AndFilter();
    }

    @Bean
    public Filter boyFilter() {
        return new BoyFilter();
    }
}
