package com.panghu.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理，捕获所有Controller中抛出的异常。
 * @author liuyin
 * @date 2022/4/21 14:08
 */
//@ControllerAdvice
//@ResponseBody
//public class GlobalExceptionHandler {
//    /**
//     * 访问无权限接口返回信息
//     * @author liuyin
//     * @date 2022/4/21 14:15
//     * @param e
//     * @return java.lang.Object
//     */
//    @ExceptionHandler(AuthorizationException.class)
//    public Object exceptionHandler(Exception e){
//        e.printStackTrace();
//        return "无权限哦！";
//    }
//}