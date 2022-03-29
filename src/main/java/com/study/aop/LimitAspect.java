package com.study.aop;

import cn.hutool.core.util.StrUtil;
import com.study.R.ResultBuilder;
import com.study.limit.AccessLimiter;
import com.study.limit.GenerateRedisKey;
import com.study.limit.LimitParam;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 切面的实现，实现拦截之后的屏蔽和限流的逻辑
 *
 * 经过并发测试，无问题
 */
@Slf4j
@Aspect
@Component
public class LimitAspect {
    @Autowired
    private AccessLimiter limiter;

    @Autowired
    GenerateRedisKey generateRedisKey;
    @Autowired
    LimitParam limitParam;


    @Pointcut("@annotation(com.study.limit.Limit)")
    public void limitPointcut() {
    }

    private static String getRedisKeyCount = "{0}:*";
    private static String setRedisKey = "{0}:{1}";

    @Around("limitPointcut()")
    public Object doArround(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer userId=101;//每个用户请求的标识
        Lock lock=new ReentrantLock();
        synchronized (userId) {
            String redisKey = generateRedisKey.getMethodUrlConvertRedisKey(joinPoint,userId);
            if (Objects.nonNull(redisKey)) {
                int count = limiter.getRedisKeyCount(StrUtil.indexedFormat(getRedisKeyCount, redisKey));
                if (count > 0) {
                    int limitTimes = limitParam.getLimitTimes(joinPoint);
                    if (count + 1 > limitTimes) {
                        return ResultBuilder.failed("method is closed, key:" + redisKey);
                    }
                }

                long redisKeyExpirationTime = limitParam.getRedisKeyExpirationTime(joinPoint);
                limiter.setValue(StrUtil.indexedFormat(setRedisKey, redisKey, new Random().nextInt(99999)), redisKeyExpirationTime);
            }
        }
        Object result = null;
        result = joinPoint.proceed();

        return result;
    }
}
