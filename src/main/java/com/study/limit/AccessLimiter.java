package com.study.limit;

import java.util.concurrent.TimeUnit;

public interface AccessLimiter {
    /**
     * 检查指定的key是否收到访问限制
     * @param key   限制接口的标识
     * @param times 访问次数
     * @param per   一段时间
     * @param unit  时间单位
     * @return
     */
    public boolean isLimited(String key, long times, long per, TimeUnit unit);




    /**
     * 判断Redis的key是否存在
     * @param redisKey
     * @return
     */
    public boolean existsKey(String redisKey);

    /**
     * 设key的过期时间
     * @param redisKey
     * @param expirationTime
     * @return
     */
    public boolean setValue(String redisKey,long expirationTime);

    int getRedisKeyCount(String redisKey);
}
