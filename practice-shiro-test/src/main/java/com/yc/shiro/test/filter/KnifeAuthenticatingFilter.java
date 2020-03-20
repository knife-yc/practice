package com.yc.shiro.test.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 认证器
 */
public class KnifeAuthenticatingFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        System.out.println("KnifeAuthenticatingFilter.createToken");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        usernamePasswordToken.setPassword("knife".toCharArray());
        return usernamePasswordToken;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//        WebUtils.issueRedirect(request, response, "/home.jsp");
        System.out.println("KnifeAuthenticatingFilter onLoginSuccess");
        WebUtils.issueRedirect(request, response, getSuccessUrl());
        return false;
    }

    //可以不用重写，父类中的逻辑：校验访问url的用户是否已经认证过，有则返回true，没有则校验本次访问的url，不是登陆url且时
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //定义允许访问的规则，返回false之后会调用onAccessDenied方法，
        System.out.println("KnifeAuthenticatingFilter.isAccessAllowed,mappedValue:" + mappedValue);
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        System.out.println("KnifeAuthenticatingFilter.onAccessDenied");
//        WebUtils.issueRedirect(servletRequest, servletResponse, "/error.jsp");
        return super.executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("KnifeAuthenticatingFilter onLoginFailure");
        return super.onLoginFailure(token, e, request, response);
    }
}
