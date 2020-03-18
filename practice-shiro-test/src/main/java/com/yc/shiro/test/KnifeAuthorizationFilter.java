package com.yc.shiro.test;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 授权器
 */
public class KnifeAuthorizationFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse, Object o) throws Exception {
        System.out.println("KnifeAuthorizationFilter.isAccessAllowed");
        return false;
    }


}
