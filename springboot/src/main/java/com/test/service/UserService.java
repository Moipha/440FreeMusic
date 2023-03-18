package com.test.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.common.Constants;
import com.test.common.Result;
import com.test.common.ServiceException;
import com.test.dto.UserDTO;
import com.test.mapper.UserMapper;
import com.test.pojo.User;
import com.test.utils.TokenUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    //登录实现
    public Result login(UserDTO userDTO) {
        //获取密码和用户名
        String password = userDTO.getPassword();
        String username = userDTO.getUsername();
        String email = userDTO.getEmail();
        //排除字符串为空的情况
        if (StrUtil.isBlank(password) || StrUtil.isBlank(username)) {
            return Result.error(Constants.CODE_400, "用户名或密码为空");
        }
        //进行判断
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //判断传入的用户名还是邮箱
        String emailStander = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        if (username.matches(emailStander)) {
            queryWrapper.eq("email", username);
        } else {
            queryWrapper.eq("username", username);
        }
        queryWrapper.eq("password", password);
        User user = getOne(queryWrapper);
        if (user == null) {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        } else {
            //把查询到的对应的用户上的信息copy到只有用户名和密码的userDTO对象上
            BeanUtil.copyProperties(user, userDTO, true);
            //设置token
            String token = TokenUtils.genToken(user.getId().toString(), user.getPassword());
            userDTO.setToken(token);
            return Result.success(userDTO);
        }
    }

    public Result register(UserDTO userDTO) {
        //获取用户名、邮箱、密码
        String username = userDTO.getUsername();
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        //进行判断，如果用户名或者邮箱有重复则抛出异常
        QueryWrapper<User> qw = new QueryWrapper<>();
        //筛选用户名相同或者邮箱相同的用户
        qw.eq("username", username).or().eq("email", email);
        //如果有重复抛出异常
        if (getOne(qw) != null) {
            throw new ServiceException("400", "已存在相同用户名或者邮箱的用户");
        } else {
            //无重复则进行注册
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            save(user);
        }
        return Result.success();
    }

    public Result edit(UserDTO userDTO) {
        //获取对应id的用户
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("id", userDTO.getId());
        User user = getOne(qw);
        if (user != null) {
            //将dto中的属性值copy到刚获取的user对象中去
            BeanUtil.copyProperties(userDTO, user, true);
        }
        //排除昵称过长的情况
        assert user != null;
        if(user.getNickname().length()>50){
            throw new ServiceException("400","昵称长度不可超过50");
        }

        if(updateById(user)){
            return Result.success();
        }else{
            return Result.error("600","写入数据库的过程中失败");
        }
    }
}
