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
@TableName("user_list")
@ToString

public class UserList {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer listId;
    private Integer userId;
    private Boolean type;
    private Date createTime;
}
