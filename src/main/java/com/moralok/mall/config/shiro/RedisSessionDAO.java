package com.moralok.mall.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author moralok
 * @since 2020/8/21 下午1:48
 */
@Slf4j
public class RedisSessionDAO extends AbstractSessionDAO {

    private static final String DEFAULT_SESSION_KEY_PREFIX = "shiro:session:";
    private String keyPrefix = DEFAULT_SESSION_KEY_PREFIX;

    @Autowired
    private RedisTemplate<String, Object> objectRedisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        log.info("create session");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        objectRedisTemplate.opsForValue().set(getRedisSessionKey(session.getId()), session, session.getTimeout(), TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        log.info("read session");
        return (Session) objectRedisTemplate.opsForValue().get(getRedisSessionKey(sessionId));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.info("update session");
        if (session != null && session.getId() != null) {
            objectRedisTemplate.opsForValue().set(getRedisSessionKey(session.getId()), session, session.getTimeout(), TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void delete(Session session) {
        log.info("delete session");
        if (session != null && session.getId() != null) {
            objectRedisTemplate.opsForValue().getOperations().delete(getRedisSessionKey(session.getId()));
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.info("batch read session");
        Set<Session> sessions = new HashSet<>();
        Set<String> keys = objectRedisTemplate.keys(this.keyPrefix + "*");
        if (keys != null && keys.size() > 0) {
            for (String key : keys) {
                Session session = (Session) objectRedisTemplate.opsForValue().get(key);
                sessions.add(session);
            }
        }
        return sessions;
    }

    private String getRedisSessionKey(Serializable sessionId) {
        return this.keyPrefix + sessionId;
    }
}
