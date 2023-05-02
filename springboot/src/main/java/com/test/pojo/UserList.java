package com.test.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_list")
@ToString

public class UserList {
    private Integer listId;
    private Integer userId;
    private Boolean type;
}
