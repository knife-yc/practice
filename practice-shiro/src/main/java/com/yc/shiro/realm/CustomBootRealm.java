package com.yc.shiro.realm;

import com.yc.shiro.exception.AuthException;
import com.yc.shiro.service.UserService;
import com.yc.shiro.token.CustomAuthenticationToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomBootRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principalCollection 用户的身份信息
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-----------授权CustomBootRealm.doGetAuthorizationInfo executed-------------");
        //TODO:
        principalCollection.asList();

        return null;
    }

    /**
     * 认证
     *
     * @param authenticationToken 用户提交的身份及凭据
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("----------------CustomBootRealm.doGetAuthenticationInfo executed--------------------");
        assert authenticationToken instanceof CustomAuthenticationToken;
        String principal = (String) authenticationToken.getPrincipal();
        if (principal == null) {
            throw new AuthException(-1,"user not exits");
        }
        String credentials = userService.getPasswordByName(principal);

        return new SimpleAuthenticationInfo(principal, credentials, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        System.out.println("----------------CustomBootRealm.supports executed--------------------");
        return token != null && token instanceof CustomAuthenticationToken;
    }
}
