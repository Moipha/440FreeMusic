package com.test.controller;

import com.test.common.Result;
import com.test.dto.UserDTO;
import com.test.pojo.User;
import com.test.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    //登录
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO user){
        return userService.login(user);
    }
    //注册
    @PostMapping("/register")
    public Result register(@RequestBody UserDTO user){
        return userService.register(user);
    }

    //更新
    @PostMapping("/edit")
    public Result edit(@RequestBody UserDTO user){
        return userService.edit(user);
    }
}
