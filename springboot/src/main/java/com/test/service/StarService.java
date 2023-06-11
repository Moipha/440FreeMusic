package com.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.common.Result;
import com.test.mapper.StarMapper;
import com.test.pojo.Music;
import com.test.pojo.Star;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StarService extends ServiceImpl<StarMapper, Star> {
    @Resource
    MusicService musicService;

    //收藏歌曲
    public Result starMusic(Star star) {
        //先检查是否已经收藏，如果已收藏过就取消收藏
        QueryWrapper<Star> qw = new QueryWrapper<>();
        qw.eq("user_id", star.getUserId());
        qw.eq("music_id", star.getMusicId());
        Star one = getOne(qw);
        if (one != null) {
            //收藏过
            remove(qw);
        } else {
            //未收藏
            save(star);
        }
        return Result.success(one == null);
    }

    //获取所有收藏
    public Result getStars(Star star) {
        QueryWrapper<Star> qw = new QueryWrapper<>();
        qw.eq("user_id", star.getUserId());
        List<Star> stars = list(qw);
        List<Music> result = new ArrayList<>();
        for (Star s : stars) {
            result.add(musicService.getById(s.getMusicId()));
        }
        return Result.success(result);
    }

    //查询指定音乐是否被收藏
    public Result searchStar(Star star) {
        QueryWrapper<Star> qw = new QueryWrapper<>();
        qw.eq("user_id", star.getUserId());
        qw.eq("music_id", star.getMusicId());
        Star one = getOne(qw);
        if (one != null) {
            //已收藏
            return Result.success(true);
        } else {
            //未收藏
            return Result.success(false);
        }
    }

    //获取用户收藏数
    public Result getStarCount(Integer userId) {
        QueryWrapper<Star> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        List<Star> stars = list(qw);
        return Result.success(stars.size());
    }
}
