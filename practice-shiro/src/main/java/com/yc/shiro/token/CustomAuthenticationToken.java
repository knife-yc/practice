package com.yc.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

public class CustomAuthenticationToken implements AuthenticationToken {

    private String userName;
    private String password;

    public CustomAuthenticationToken() {
    }

    public CustomAuthenticationToken(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}
