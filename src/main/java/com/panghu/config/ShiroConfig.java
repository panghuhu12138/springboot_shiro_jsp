package com.panghu.config;

import com.panghu.shiro.cache.RedisCacheManager;
import com.panghu.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author liuyin
 * @date 2022年04月20日 10:14
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilter(DefaultWebSecurityManager securityManager) {
        //创建shiroFilter用于拦截所有请求
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //给Filter设置安全管理器
        shiroFilter.setSecurityManager(securityManager);
        //资源权限配置
        shiroFilter.setFilterChainDefinitionMap(new HashMap<String,String>(){{
            //authc 请求这个资源需要认证和授权
            this.put("/register.jsp", "anon");
            this.put("/user/**", "anon");
            this.put("/**", "authc");

        }});
        shiroFilter.setLoginUrl("/login.jsp");
        return shiroFilter;
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(Realm realm) {
        //创建安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //给安全管理器设置realm
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public Realm getRealm() {
        //创建自定义认证器
        CustomerRealm realm = new CustomerRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1024);
        realm.setCredentialsMatcher(matcher);
        //开启缓存管理
        realm.setCacheManager(redisCacheManager);
        //开启全局缓存，默认开启
        realm.setCachingEnabled(true);
        //开启认证缓存
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthenticationCacheName("authenticationCache");
        //开启授权缓存
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCacheName("authorizationCache");
        return realm;
    }
}
