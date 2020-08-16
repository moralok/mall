package com.moralok.mall.log;

import lombok.Data;

import java.util.List;

/**
 * @author moralok
 * @since 2020/8/16 下午3:07
 */
@Data
public class WebLog {

    private String description;

    private Long startTime;

    private Integer spendTime;

    private String basePath;

    private String uri;

    private String url;

    private String method;

    private String ip;

    private List<Object> parameter;

    private Object result;

}
