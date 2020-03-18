package com.yc.shiro.test;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class KnifeRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权doGetAuthorizationInfo");
        return new SimpleAuthorizationInfo();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        System.out.println("KnifeRealm supports");
        return true;
    }

    //在filter中执行executeLogin方法时会调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证KnifeRealm doGetAuthenticationInfo");
        try {
            //token:根据用户传入的登录名/密码
            //credentials:查询数据库或者调用第三方服务获取的用户名/密码
            return new SimpleAuthenticationInfo(token, "knife", getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
