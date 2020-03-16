package com.yc.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

/**
 * 认证器
 */
public class KnifeAuthenticatingFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        return null;
    }

    @Override
    protected boolean onAccessDenied(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse) throws Exception {
        return false;
    }
}
