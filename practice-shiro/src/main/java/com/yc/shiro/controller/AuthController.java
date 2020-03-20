package com.yc.shiro.controller;

import com.yc.shiro.exception.AuthException;
import com.yc.shiro.token.CustomAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public void login(String userName, String password) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            CustomAuthenticationToken token = new CustomAuthenticationToken(userName, password);
            try {
                subject.login(token);
            } catch (Exception e) {
                if (e instanceof AuthException) {
                    System.out.println("code:" + ((AuthException) e).getCode() + ",msg:" + ((AuthException) e).getMsg());
                }
            }
        }
        System.out.println("-------------login---------------");
    }

    @RequestMapping("/unauthc")
    public HttpStatus unauthc() {
        System.out.println("-------------unauthc---------------");
        return HttpStatus.UNAUTHORIZED;

    }

    @RequestMapping("/success")
    public void success() {
        System.out.println("-------------success---------------");
    }


}
