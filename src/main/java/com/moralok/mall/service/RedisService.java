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
     * @param expire 过期时间
     */
    void set(String key, Serializable value, long expire);

    /**
     * 设置值
     *
     * @param key key，String
     * @param value value，Serializable
     * @param expire 过期时间
     * @param timeUnit 时间单位
     */
    void set(String key, Serializable value, long expire, TimeUnit timeUnit);

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

    /**
     * 对 name 进行加锁，如果该锁被其他线程占用则最多等待 waitTime 时间返回是否加锁成功
     *
     * @param name 锁名称
     * @param waitTime 等待时间
     * @param leaseTime 加锁成功后 leaseTime 时间后自动解锁
     * @param timeUnit 时间单位
     * @return 成功与否
     * @throws InterruptedException
     */
    boolean tryLock(String name, long waitTime, long leaseTime, TimeUnit timeUnit);

    /**
     * 解锁 必须是该锁拥有者
     *
     * @param name name 锁名称
     * @return 成功与否
     */
    boolean unlock(String name);
}
