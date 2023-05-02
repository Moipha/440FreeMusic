package com.test.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.pojo.List;
import com.test.pojo.UserList;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserListMapper extends BaseMapper<UserList> {

}
