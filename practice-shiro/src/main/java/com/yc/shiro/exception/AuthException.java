package com.yc.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class AuthException extends AuthenticationException {

    private Integer code;
    private String msg;

    public AuthException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
