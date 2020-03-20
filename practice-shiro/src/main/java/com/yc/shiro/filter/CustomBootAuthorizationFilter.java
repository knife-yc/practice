package com.yc.shiro.filter;

import com.yc.shiro.realm.CustomBootRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * 授权器
 */
public class CustomBootAuthorizationFilter extends AuthorizationFilter {

    //在本方法调用subject.hasRole()或者subject.checkPermission();方法才会执行realm中的授权方法

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param o      配置中映射的值
     * @return
     * @throws IOException
     */
    @Override
    public boolean isAccessAllowed(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse, Object o) throws IOException {
        System.out.println("----------授权CustomBootAuthorizationFilter.isAccessAllowed----------");
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            //没有登陆就调用本次接口
            return false;
        }

        return subject.hasRole("admin");
    }
}
