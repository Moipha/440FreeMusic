package com.test.dto;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * 用于接收前端登录请求的参数
 */
@Data
public class UserDTO {
    //使用Alias注解给各个字段设置成与User对象相同的别名，这样在使用BeanUtil的copyProperties方法时就不会出错了
    private Integer id;
    @Alias("用户名")
    private String username;
    @Alias("密码")
    private String password;
    @Alias("昵称")
    private String nickname;
    @Alias("邮箱")
    private String email;
    @Alias("创建时间")
    private Date createTime;

    private String token;
    private List<com.test.pojo.List> lists;

}
