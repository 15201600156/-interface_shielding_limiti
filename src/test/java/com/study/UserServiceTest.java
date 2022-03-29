package com.study;

import com.study.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    UserService userService;

    @Autowired
    StringRedisTemplate redisTemplate;
    @Test
    public void getUser() {

       userService.getUser();
        System.out.println(111);
    }
}
