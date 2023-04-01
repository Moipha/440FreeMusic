package com.test.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.common.Result;
import com.test.mapper.AvatarMapper;
import com.test.pojo.Avatar;
import com.test.pojo.Music;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class AvatarService extends ServiceImpl<AvatarMapper, Avatar> {

    @Value("${files.upload.path}avatars/")
    private String fileUploadPath;

    //保存封面文件
    public Result saveAvatar(MultipartFile file) throws IOException {
        //TODO 需要在返回的对象中封装src和md5两个属性
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
        QueryWrapper<Avatar> qw = new QueryWrapper<>();
        qw.eq("md5", md5);
        List<Avatar> avatars = list(qw);
        Avatar avatar;
        if (avatars.size() != 0) {
            avatar = list(qw).get(0);
        } else {
            avatar = null;
        }
        //进行判断
        if (avatar != null) {
            //如果已存在相同md5的文件，删除新存入的文件
            url = avatar.getUrl();
            uploadFile.delete();
        } else {
            //不存在，那就在服务器中保存
            url = "http://localhost:8080/avatar/" + fileUuid;
            //TODO 此处在部署时需要修改

            //将封面存入数据库
            Avatar dbData = new Avatar();
            dbData.setName(originalFilename);
            dbData.setSize((int) file.getSize() / 1024);
            dbData.setMd5(md5);
            dbData.setUrl(url);
            save(dbData);
        }

        //然后封装对象返回即可
        Music result = new Music();
        result.setUrl(url);
        return Result.success(result);
    }


    /**
     * 下载文件接口
     *
     * @param fileUuid 文件后缀，包括uid和后缀名
     * @param response http响应流
     * @throws IOException 抛出获取输出流和操作流时的异常
     */
    public void download(String fileUuid, HttpServletResponse response) throws IOException {
        //根据文件的唯一标识码来获取文件
        File uploadFile = new File(fileUploadPath + fileUuid);
        //设置输出流的格式
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode((fileUuid), "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        outputStream.write(FileUtil.readBytes(uploadFile));
        outputStream.flush();
        outputStream.close();
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
    }


}
