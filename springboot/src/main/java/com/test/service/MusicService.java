package com.test.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.test.common.Result;
import com.test.mapper.MusicMapper;
import com.test.pojo.Music;
import com.test.utils.IntToTimeString;
import com.test.utils.MediaHttpRequestHandler;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class MusicService extends ServiceImpl<MusicMapper, Music> {
    //获取文件上传位置
    @Value("${files.upload.path}musics/")
    private String fileUploadPath;

    @Resource
    private MusicMapper musicMapper;

    @Resource
    private MediaHttpRequestHandler mediaHttpRequestHandler;

    //获取上传音乐的信息
    public Result getData(MultipartFile multipartFile) throws InvalidDataException, UnsupportedTagException, IOException, CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {
        //创建临时文件
        File file = File.createTempFile(Objects.requireNonNull(multipartFile.getOriginalFilename()), null);
        //将MultipartFile对象的内容复制到临时文件中
        multipartFile.transferTo(file);
        //创建music对象
        Music music = new Music();
        //设置名称、作者、专辑、文件大小、时长
        music.setSize((int) (file.length() / 1024));  //单位为kb
        //获取文件后缀
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename != null && originalFilename.endsWith(".mp3")) {
            //创建Mp3File对象
            Mp3File mp3File = new Mp3File(file);
            //处理MP3文件的逻辑
            if (mp3File.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3File.getId3v2Tag();
                music.setName(id3v2Tag.getTitle());
                music.setAuthor(id3v2Tag.getArtist());
                music.setAlbum(id3v2Tag.getAlbum());
            }
            music.setTime(IntToTimeString.Method((int) mp3File.getLengthInSeconds()));   //单位为秒
        } else {
            //如果是FLAC格式
            FlacFileReader flacFileReader = new FlacFileReader();
            AudioFile audioFile = flacFileReader.read(file);
            Tag tag = audioFile.getTag();
            music.setName(tag.getFirst("TITLE"));
            music.setAuthor(tag.getFirst("ARTIST"));
            music.setAlbum(tag.getFirst("ALBUM"));
            music.setTime(IntToTimeString.Method(audioFile.getAudioHeader().getTrackLength())); //单位为秒
        }


        return Result.success(music);
    }

    //保存音频文件至服务器
    public Result saveMusic(MultipartFile file) throws IOException {
        //是否需要存入数据库
        boolean needSaveDB;
        //要返回的结果
        Music result = new Music();
        //使用hutool获取文件类型
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        //使用hutool为每个文件定义唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUuid = uuid + StrUtil.DOT + type;
        //新建文件，路径为设定值
        File uploadFile = new File(fileUploadPath + fileUuid);
        //判断，如果父目录不存在就创建
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }
        //定义要封装的两个字段
        String url;
        String md5;
        //将文件存入磁盘中
        file.transferTo(uploadFile);
        //获取文件的md5。然后根据md5先去数据库中查询文件
        md5 = SecureUtil.md5(uploadFile);
        QueryWrapper<Music> qw = new QueryWrapper<>();
        qw.eq("md5", md5);
        List<Music> musics = list(qw);
        Music music;
        if (musics.size() != 0) {
            music = list(qw).get(0);
        } else {
            music = null;
        }
        //进行判断
        if (music != null) {
            //如果已存在相同md5的文件，删除新存入的文件
            url = music.getUrl();
            uploadFile.delete();
            needSaveDB = false;
            //获取更新需要的id
            result.setId(music.getId());
        } else {
            //不存在，那就在服务器中保存
            needSaveDB = true;
            url = "http://localhost:8080/music/" + fileUuid;
            //TODO 此处在部署时需要修改
        }
        //然后封装对象返回即可

        result.setUrl(url);
        result.setMd5(md5);
        result.setEnable(needSaveDB);
        return Result.success(result);
    }

    /**
     * 下载文件接口
     *
     * @param fileUuid 文件后缀，包括uid和后缀名
     * @param response http响应流
     * @throws IOException 抛出获取输出流和操作流时的异常
     */
    public void download(String fileUuid, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        //根据文件的唯一标识码来获取文件
        File uploadFile = new File(fileUploadPath + fileUuid);
        //设置输出流的格式
        ServletOutputStream outputStream = response.getOutputStream();
        // 设置响应头
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUuid, "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setHeader(HttpHeaders.CACHE_CONTROL, CacheControl.noCache().getHeaderValue());
        response.setHeader(HttpHeaders.PRAGMA, "");
        response.addHeader("Content-Length", String.valueOf(uploadFile.length()));
        response.setDateHeader(HttpHeaders.EXPIRES, 0);
        Path path = Paths.get(fileUploadPath + fileUuid);
        request.setAttribute(MediaHttpRequestHandler.ATTR_FILE, path);
        mediaHttpRequestHandler.handleRequest(request, response);
        //读取文件的字节流
        outputStream.write(FileUtil.readBytes(uploadFile));
        outputStream.flush();
        outputStream.close();
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
    }

    //存入数据库
    public Result saveDB(Music music) {
        saveOrUpdate(music);
        return Result.success();
    }


    public Result searchByName(String keyword) {
        //通过标题模糊查询
        QueryWrapper<Music> qw = new QueryWrapper<>();
        qw.like("name", keyword);
        qw.eq("enable", true);
        qw.eq("is_delete", false);
        List<Music> list = list(qw);
        return Result.success(list);
    }

    //获取随机七首曲子
    public Result getRandom() {
        //先获取所有曲子
        List<Music> all = list();
        if (all.size() <= 7) {
            //如果所有上传的曲子加一块都没七个，那就直接返回
            return Result.success(all);
        } else {
            //否则开始随机获取
            List<Music> randomSongs = new ArrayList<>();
            Random random = new Random();
            while (randomSongs.size() < 7) {
                int randomIndex = random.nextInt(all.size());
                Music randomSong = all.get(randomIndex);
                if (!randomSongs.contains(randomSong)) {
                    randomSongs.add(randomSong);
                }
            }
            return Result.success(randomSongs);
        }
    }
}
