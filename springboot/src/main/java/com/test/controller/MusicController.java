package com.test.controller;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.test.common.Result;
import com.test.pojo.Music;
import com.test.service.MusicService;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Resource
    MusicService musicService;

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

    //上传保存封面
    @PostMapping("/saveAvatar")
    public Result saveAvatar(@RequestParam MultipartFile file) throws IOException {
        return musicService.saveAvatar(file);
    }

    //将音乐信息存入数据库
    @PostMapping("/saveDB")
    public Result saveDB(@RequestBody Music music) {
        return musicService.saveDB(music);
    }

    //下载指定音乐
    @GetMapping("/{fileUuid}")
    public void download(@PathVariable String fileUuid, HttpServletResponse response) throws IOException {
        musicService.download(fileUuid, response);
    }
}
