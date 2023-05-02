package com.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.common.Result;
import com.test.common.ServiceException;
import com.test.mapper.ListMapper;
import com.test.mapper.ListMusicMapper;
import com.test.mapper.MusicMapper;
import com.test.mapper.UserListMapper;
import com.test.pojo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class ListService extends ServiceImpl<ListMapper, List> {

    @Resource
    UserListMapper userListMapper;
    @Resource
    UserService userService;
    @Resource
    ListMusicMapper listMusicMapper;
    @Resource
    MusicMapper musicMapper;


    //添加歌单
    public Result addList(List list, Integer userId) {
        //获取该用户的所有歌单
        java.util.List<List> lists = userService.getLists(userId);
        for (List l : lists) {
            //检查是否有重名歌单
            if (l.getTitle().equals(list.getTitle())) {
                throw new ServiceException("400", "已存在相同标题的歌单");
            }
        }
        //无重名就添加
        list.setAuthorId(userId);
        save(list);
        //然后添加用户-歌单
        UserList userList = new UserList();
        Integer listId = getOne(new QueryWrapper<List>().eq("title", list.getTitle())).getId();
        userList.setListId(listId);
        userList.setUserId(userId);
        userListMapper.insert(userList);

        return Result.success();
    }

    //根据歌单标题获取该歌单的所有信息
    public Result getData(String title, String userId) {
        //先获取该用户的所有歌单
        java.util.List<List> lists = userService.getLists(Integer.valueOf(userId));
        QueryWrapper<List> qw = new QueryWrapper<>();
        qw.eq("title", title);
        java.util.List<Integer> ids = new ArrayList<>();
        for (List list : lists) {
            ids.add(list.getId());
        }
        qw.in("id", ids);
        List list = getOne(qw);
        if (list == null) {
            throw new ServiceException("400", "不存在这样标题的歌单");
        }
        return Result.success(list);
    }

    //获取创建者信息
    public Result getAuthor(Integer userId) {
        User author = userService.getOne(new QueryWrapper<User>().eq("id", userId));
        return Result.success(author);
    }

    //获取歌单歌曲
    public Result getMusics(Integer listId) {
        java.util.List<ListMusic> listMusics = listMusicMapper.selectList(new QueryWrapper<ListMusic>().eq("list_id", listId));
        java.util.List<Music> musics = new ArrayList<>();
        for (ListMusic listMusic : listMusics) {
            musics.add(musicMapper.selectOne(new QueryWrapper<Music>().eq("id", listMusic.getMusicId())));
        }
        return Result.success(musics);
    }
}
