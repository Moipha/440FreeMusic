package com.test.pojo;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
@ToString

//通过@Alias注解给字段起别名，可以在excel中通过中文别名的表头识别数据
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Alias("用户名")
    private String username;
    //通过JsonIgnore注解，忽略某个字段，在json找那个不会显示。通常用在密码中
    @JsonIgnore
    @Alias("密码")
    private String password;
    @Alias("昵称")
    private String nickname;
    @Alias("邮箱")
    private String email;
    @Alias("创建时间")
    private Date createTime;
}
