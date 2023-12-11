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
@TableName("list_music")
@ToString

public class ListMusic {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Date createTime;
    private Integer listId;
    private Integer musicId;
}
