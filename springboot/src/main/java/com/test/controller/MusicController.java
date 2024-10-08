package com.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.test.common.Result;
import com.test.pojo.Music;
import com.test.pojo.Star;
import com.test.service.MusicService;
import com.test.service.StarService;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Resource
    MusicService musicService;
    @Resource
    StarService starService;

    //获取音频文件的元数据
    @PostMapping("/getData")
    public Result getData(@RequestParam MultipartFile file) throws IOException, InvalidDataException, UnsupportedTagException, TagException, CannotReadException, InvalidAudioFrameException, ReadOnlyFileException {
        return musicService.getData(file);
    }

    //上传保存音频文件
    @PostMapping("/saveMusic")
    public Result saveMusic(@RequestParam("file") MultipartFile file) throws IOException {
        return musicService.saveMusic(file);
    }


    //将音乐信息存入数据库
    @PostMapping("/saveDB")
    public Result saveDB(@RequestBody Music music) {
        return musicService.saveDB(music);
    }

    //下载指定音乐
    @GetMapping("/{fileUuid}")
    public void download(@PathVariable String fileUuid, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        musicService.download(fileUuid, response, request);
    }

    //查询
    @PostMapping("/searchByName")
    public Result searchByName(@RequestBody Music music) {
        return musicService.searchByName(music.getName());
    }

    //获取所有歌曲
    @GetMapping("/getAll")
    public Result getAll() {
        return Result.success(musicService.list(new QueryWrapper<Music>().orderByDesc("create_time")));
    }

    //收藏歌曲
    @PostMapping("/starMusic")
    public Result starMusic(@RequestBody Star star){
        return starService.starMusic(star);
    }

    //查询指定音乐是否被用户收藏
    @PostMapping("/searchStar")
    public Result searchStar(@RequestBody Star star){
        return starService.searchStar(star);
    }

    //获取随机的7首曲子
    @GetMapping("/getRandom")
    public Result getRandom(){
        return musicService.getRandom();
    }
}
