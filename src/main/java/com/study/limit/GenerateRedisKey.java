package com.study.limit;

import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class GenerateRedisKey {
    private static String redisKey = "{0}:{1}";
    public String getMethodUrlConvertRedisKey(ProceedingJoinPoint joinPoint, Integer userId) {
        Method m = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RequestMapping methodAnnotation = m.getAnnotation(RequestMapping.class);
        if (methodAnnotation != null) {
            String[] methodValue = methodAnnotation.value();
            String dscUrl = methodValue[0];
//            List<String> strings = Arrays.asList("token1", "token2", "token3");
//            return  StrUtil.indexedFormat(redisKey,dscUrl,strings.get(new Random().nextInt(3)));
                     return  StrUtil.indexedFormat(redisKey,dscUrl,userId);

        }
        return redisKey.toString();
    }
}
