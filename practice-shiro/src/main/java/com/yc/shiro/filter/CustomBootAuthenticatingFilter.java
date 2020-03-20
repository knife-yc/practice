package com.yc.shiro.filter;

import com.yc.shiro.token.CustomAuthenticationToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 认证器
 */
public class CustomBootAuthenticatingFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        System.out.println("-----------CustomBootAuthenticatingFilter.createToken executed----------");
        return new CustomAuthenticationToken();
    }

    @Override
    protected boolean onAccessDenied(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        System.out.println("----------CustomBootAuthenticatingFilter.onAccessDenied executed-------------");
        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //定义通过规则
        System.out.println("----------CustomBootAuthenticatingFilter.isAccessAllowed executed-------------");
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("------------CustomBootAuthenticatingFilter.onLoginSuccess executed------------");
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("------------CustomBootAuthenticatingFilter.onLoginFailure executed------------");
        return super.onLoginFailure(token, e, request, response);
    }
}
