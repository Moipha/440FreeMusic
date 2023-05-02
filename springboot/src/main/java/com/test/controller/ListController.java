package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.common.Result;
import com.test.pojo.List;
import com.test.service.ListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/list")
public class ListController {
    @Resource
    private ListService listService;

    //添加歌单
    @PostMapping("/add")
    public Result addList(@RequestBody Map<String, Object> m) {
        //通过com.fasterxml.jackson.databind.ObjectMapper包中的方法实现从Object至自定义对象的转换
        return listService.addList(new ObjectMapper().convertValue(m.get("list"),List.class), (Integer) (m.get("userId")));
    }

    @PostMapping("/getData")
    public Result getData(@RequestBody Map<String,String> m){
        return listService.getData(m.get("title"),m.get("userId"));
    }
    @PostMapping("/getAuthor/{userId}")
    public Result getAuthor(@PathVariable Integer userId){
        return listService.getAuthor(userId);
    }

    @PostMapping("/getMusics/{listId}")
    public Result getMusics(@PathVariable Integer listId){
        return listService.getMusics(listId);
    }
}
