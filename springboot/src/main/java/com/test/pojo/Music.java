package com.test.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("music")
@ToString

public class Music {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String author;
    private String album;
    private String uploader;
    private String avatar;
    private String url;
    private Integer size;
    private String md5;
    private Boolean isDelete;
    private Boolean enable;
    private Integer time;
}
