package com.panghu.controller;

import cn.hutool.core.util.IdUtil;
import com.panghu.entity.User;
import com.panghu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


/**
 * @author liuyin
 * @date 2022年04月20日 14:42
 */
@Slf4j
@Controller
@RequestMapping("user")
public class UserController extends BaseExceptionController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用来处理身份认证
     * @author liuyin
     * @date 2022/4/20 14:43
     * @param username
     * @param password
     * @return java.lang.String
     */
    @RequestMapping("login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:/index.jsp";
        }  catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败: 用户不存在！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败: 密码错误！");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证失败:" + e.getMessage());
        }
        return "redirect:/login.jsp";
    }

    /**
     * 用户退出
     * @author liuyin
     * @date 2022/4/20 15:43
     * @return java.lang.String
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

    /**
     * 用户注册
     * @author liuyin
     * @date 2022/4/20 15:45
     * @param username
     * @param password
     * @return java.lang.String
     */
    @RequestMapping("register")
    public String register(String username, String password) {
        User user = new User();
        String uuid = IdUtil.simpleUUID();
        user.setUserId(uuid);
        user.setUsername(username);
        user.setPassword(new Md5Hash(password, uuid, 1024).toHex());
        user.setRole("user");
        userService.save(user);
        return "login";
    }


    @RequestMapping("getUsers")
    @ResponseBody
    @RequiresRoles("admin")
    public List<User> getAllUsers() {
        try {
            return userService.queryAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
