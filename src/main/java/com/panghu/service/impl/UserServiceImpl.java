package com.panghu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.panghu.entity.User;
import com.panghu.dao.UserMapper;
import com.panghu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @author panghuhu
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-04-20 16:33:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUsersByUsername(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.like(StrUtil.isNotBlank(name),"name", name);
        return userMapper.selectByMap(new HashMap<String, Object>(){{
            this.put("username", name);
        }});
    }

    @Override
    public boolean save(User user) {
        return super.save(user);
    }

    @Override
    public User queryUserByUsername(String username) {
        return this.getOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public List<User> queryAll() {
        return this.list();
    }
}
