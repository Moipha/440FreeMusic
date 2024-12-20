package com.test.controller;

import com.test.common.Result;
import com.test.dto.UserDTO;
import com.test.pojo.Star;
import com.test.pojo.User;
import com.test.service.StarService;
import com.test.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    StarService starService;
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

    //获取用户上传的音乐列表
    @GetMapping("/getUploadList/{id}")
    public Result getUploadList(@PathVariable Integer id){
        return userService.getUploadList(id);
    }

    @GetMapping("/getLists/{id}")
    public Result getLists(@PathVariable Integer id){
        return Result.success(userService.getLists(id));
    }

    //获取用户所有的收藏
    @PostMapping("/getStars")
    public Result getStars(@RequestBody Star star){
        return starService.getStars(star);
    }

    //获取用户收藏数
    @GetMapping("/getStarCount/{userId}")
    public Result getStarCount(@PathVariable Integer userId){
        return starService.getStarCount(userId);
    }
}
