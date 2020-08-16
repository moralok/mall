package com.moralok.mall.config;

import com.moralok.mall.domain.dto.Dummy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2020/8/16 下午8:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LettuceRedisConfigTests {

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testSerializable() {
        Dummy dummy = new Dummy().setId(1).setName("Test Name").setCreatedAt(LocalDateTime.now());
        serializableRedisTemplate.opsForValue().set("dummy:"+dummy.getId(), dummy);
        Dummy dummy1 = (Dummy) serializableRedisTemplate.opsForValue().get("user:"+dummy.getId());
        assert dummy.equals(dummy1);
    }

    @Test
    public void testString() {
        String key = "test:key";
        String value = "test value";
        stringRedisTemplate.opsForValue().set(key, value);
        String res = stringRedisTemplate.opsForValue().get(key);
        assert value.equals(res);
    }

    @Test
    public void testRedisson() {
        String lockName = "test:lock:name";
        RLock lock = redissonClient.getLock(lockName);
        boolean res = false;
        try {
            res = lock.tryLock(1, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.info("lockName: {} 加锁出现异常", lockName);
        }
        assert res;
        lock.unlock();
    }
}
