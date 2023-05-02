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
@TableName("list")
@ToString

public class List {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private boolean p;
    private String title;
    private String description;
    private String avatar;
    private Date createTime;
    private Integer authorId;
}
