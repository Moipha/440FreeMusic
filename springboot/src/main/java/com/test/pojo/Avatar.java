package com.test.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("avatar")
@ToString

public class Avatar {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String url;
    private Integer size;
    private String md5;
}
