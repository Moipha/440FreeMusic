package com.test.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.pojo.List;
import com.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ListMapper extends BaseMapper<List> {

}