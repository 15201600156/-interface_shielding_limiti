package com.study.limit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 接口和实现类，取Redis中的值和是否限流接口
 */
@Slf4j
@Component
public class RedisAccessLimiter implements AccessLimiter {

    @Autowired
    StringRedisTemplate redisTemplate;


    @Override
    public boolean isLimited(String key, long times, long per, TimeUnit unit) {
        Long curTimes = redisTemplate.boundValueOps(key).increment(1);
        log.info("curTimes {}", curTimes);
        if (curTimes > times) {
            log.debug("超频访问:[{}]", key);
            return true;
        } else {
            if (curTimes == 1) {
                log.info(" set expire ");
                redisTemplate.boundValueOps(key).expire(per, unit);
                return false;
            } else {
                return false;
            }
        }
    }

    /**
     * 判断Redis的key是否存在
     *
     * @param redisKey
     * @return
     */
    @Override
    public boolean existsKey(String redisKey) {
        try {
            return (boolean) redisTemplate.hasKey(redisKey);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 设key的过期时间
     *
     * @param redisKey
     * @param expirationTime
     * @return
     */
    @Override
    public boolean setValue(String redisKey, long expirationTime) {
        try {
            redisTemplate.opsForValue().set(redisKey, redisKey, expirationTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int getRedisKeyCount(String redisKey) {
        try {
            Set keys = redisTemplate.keys(redisKey);
            return keys.size();
        } catch (Exception e) {
            return 0;
        }
    }
}