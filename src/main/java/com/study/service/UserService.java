package com.study.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUser() {
        return "获取用户信息";
    }
}
