package com.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.common.Result;
import com.test.common.ServiceException;
import com.test.mapper.ListMapper;
import com.test.mapper.UserListMapper;
import com.test.pojo.List;
import com.test.pojo.UserList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserListService extends ServiceImpl<UserListMapper, UserList> {

}
