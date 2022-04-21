package com.panghu.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author liuyin
 * @date 2022年04月21日 9:34
 */

public class CustomerException extends AuthenticationException {
    //异常对应的返回码
    private String retCd;
    //异常对应的描述信息
    private String msgDes;

    public CustomerException() {
        super();
    }

    public CustomerException(String message) {
        super(message);
        msgDes = message;
    }

    public CustomerException(String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd() {
        return retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }
}