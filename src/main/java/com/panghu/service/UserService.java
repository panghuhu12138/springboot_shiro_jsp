package com.panghu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.panghu.entity.User;

import java.util.List;

/**
* @author panghuhu
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-04-20 16:33:58
*/
public interface UserService {

    public List<User> queryUsersByUsername(String name);

    boolean save(User user);

    User queryUserByUsername(String username);

    List<User> queryAll();
}
