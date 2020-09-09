package com.moralok.mall.service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Redis Service
 *
 * @author moralok
 * @since 2020/9/9 3:36 下午
 */
public interface RedisService {

    /**
     * 设置值
     *
     * @param key key，String
     * @param value value，Serializable
     */
    void set(String key, Serializable value);

    /**
     * 设置值
     *
     * @param key key，String
     * @param value value，Serializable
     * @param expire
     */
    void set(String key, Serializable value, Integer expire);

    /**
     * 设置值
     *
     * @param key key，String
     * @param value value，Serializable
     * @param expire
     * @param timeUnit
     */
    void set(String key, Serializable value, Integer expire, TimeUnit timeUnit);

    /**
     * 查询
     *
     * @param key key
     * @return
     */
    Serializable get(String key);

    /**
     * 移除
     *
     * @param key key
     */
    void remove(String key);
}
