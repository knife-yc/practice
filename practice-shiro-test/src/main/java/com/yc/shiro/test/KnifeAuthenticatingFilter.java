package com.yc.shiro.test;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 认证器
 */
public class KnifeAuthenticatingFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        System.out.println("KnifeAuthenticatingFilter.createToken");
        return new UsernamePasswordToken();
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, "/home.jsp");
        return false;
    }

    @Override
    protected boolean onAccessDenied(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        System.out.println("KnifeAuthenticatingFilter.onAccessDenied");
        WebUtils.issueRedirect(servletRequest, servletResponse, "/error.jsp");
        return false;
    }

    @Override
    public void setSuccessUrl(String successUrl) {
        super.setSuccessUrl("/home.jsp");
    }
}
