package com.study.limit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class LimitParam
{
    public long getRedisKeyExpirationTime(ProceedingJoinPoint joinPoint) {
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Limit annotation = m.getAnnotation(Limit.class);
        if (annotation != null) {
            long expirationTime = annotation.expireSeconds();
            return expirationTime;
        }
        return 0;
    }

    public int getLimitTimes(ProceedingJoinPoint joinPoint) {
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Limit annotation = m.getAnnotation(Limit.class);
        if (annotation != null) {
            int limitTimes = annotation.value();
            return limitTimes;
        }
        return 0;
    }
}
