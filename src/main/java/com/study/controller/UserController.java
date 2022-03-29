package com.study.controller;

import com.study.R.Result;
import com.study.R.ResultBuilder;
import com.study.limit.Limit;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("getUser")
    @Limit(value = 10,expireSeconds = 60)
    public Result getUser() {


        UserController.class.isAnnotationPresent(Limit.class);
        return ResultBuilder.success(userService.getUser());
    }
}
