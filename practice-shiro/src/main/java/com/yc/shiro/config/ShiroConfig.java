package com.yc.shiro.config;

import com.yc.shiro.filter.CustomBootAuthenticatingFilter;
import com.yc.shiro.filter.CustomBootAuthorizationFilter;
import com.yc.shiro.realm.CustomBootRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${shiro.enabled}")
    private Boolean enabled;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("------shiro.enabled:" + enabled);
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
        shiroFilterFactoryBean.setLoginUrl("/auth/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/unauthc");
        shiroFilterFactoryBean.setSuccessUrl("/auth/success");

        shiroFilterFactoryBean.setFilters(getFilters());

        filterChainDefinitionMap.put("/test/test", "authc");
        filterChainDefinitionMap.put("/test/testPerm", "perms[admin,system]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public CustomBootRealm shiroRealm() {
        CustomBootRealm shiroRealm = new CustomBootRealm();
        return shiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    private Map<String, Filter> getFilters() {
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", authcFilter());
        filters.put("perms", permFilter());
        return filters;
    }

    @Bean
    public AuthenticatingFilter authcFilter() {
        CustomBootAuthenticatingFilter customBootAuthenticatingFilter = new CustomBootAuthenticatingFilter();
        return customBootAuthenticatingFilter;
    }

    @Bean
    public AuthorizationFilter permFilter() {
        CustomBootAuthorizationFilter customBootAuthorizationFilter = new CustomBootAuthorizationFilter();
        return customBootAuthorizationFilter;
    }

//    @Bean
//    public PasswordHelper passwordHelper() {
//        return new PasswordHelper();
//    }
//        @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME); // 散列算法
//        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS); // 散列次数
//        return hashedCredentialsMatcher;
//    }
}
