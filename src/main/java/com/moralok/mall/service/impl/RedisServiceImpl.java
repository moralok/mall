package com.moralok.mall.service.impl;

import com.moralok.mall.domain.constant.ResultCode;
import com.moralok.mall.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2020/9/9 3:39 下午
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void set(String key, Serializable value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Serializable value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Serializable value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    @Override
    public Serializable get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String key) {
        Boolean res = redisTemplate.delete(key);
        if (res == null || Boolean.FALSE.equals(res)) {
            throw ResultCode.DELETE_REDIS_KEY_FAILED.generateException();
        }
    }

    @Override
    public boolean tryLock(String name, long waitTime, long leaseTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(name);
        boolean res = false;
        try {
            res = lock.tryLock(waitTime, leaseTime, timeUnit);
        } catch (InterruptedException e) {
            log.info("name: {} 加锁出现异常 {}", name, e.getMessage());
        }
        return res;
    }

    @Override
    public boolean unlock(String name) {
        RLock lock = redissonClient.getLock(name);
        try {
            lock.unlock();
        } catch (IllegalMonitorStateException e) {
            log.info("name: {} 解锁失败，不是该锁的拥有者!", name);
            return false;
        }
        return true;
    }
}
