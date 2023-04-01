package com.test.controller;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.test.common.Result;
import com.test.pojo.Music;
import com.test.service.AvatarService;
import com.test.service.MusicService;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    @Resource
    AvatarService avatarService;

    //上传保存封面
    @PostMapping("/saveAvatar")
    public Result saveAvatar(@RequestParam MultipartFile file) throws IOException {
        return avatarService.saveAvatar(file);
    }

    //下载指定封面
    @GetMapping("/{fileUuid}")
    public void download(@PathVariable String fileUuid, HttpServletResponse response) throws IOException {
        avatarService.download(fileUuid, response);
    }

}
