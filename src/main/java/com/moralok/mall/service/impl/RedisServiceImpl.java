package com.moralok.mall.service.impl;

import com.moralok.mall.domain.constant.ResultCode;
import com.moralok.mall.service.RedisService;
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
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void set(String key, Serializable value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Serializable value, Integer expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Serializable value, Integer expire, TimeUnit timeUnit) {
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
}
