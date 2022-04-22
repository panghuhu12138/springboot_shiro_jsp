package com.panghu.shiro.realms;

import com.panghu.entity.User;
import com.panghu.exception.CustomerException;
import com.panghu.service.UserService;
import com.panghu.shiro.cache.MyByteSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyin
 * @date 2022年04月19日 13:21
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @author liuyin
     * @date 2022/4/21 9:49
     * @param principalCollection
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        User user = userService.queryUserByUsername((String) primaryPrincipal);
        String[] arr = user.getRole().split(",");
        return new SimpleAuthorizationInfo(Arrays.stream(arr).collect(Collectors.toSet()));
    }


    /**
     * 认证方法
     * @author liuyin
     * @date 2022/4/21 9:49
     * @param authenticationToken
     * @return org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object principal = authenticationToken.getPrincipal();
        List<User> users = userService.queryUsersByUsername((String) principal);
        if (users.size() == 1) {
            User user = users.get(0);
            return new SimpleAuthenticationInfo(principal,
                    user.getPassword(),
                    new MyByteSource(user.getUserId().getBytes()),
                    this.getName());
        } else if (users.size() > 1) {
            throw new CustomerException("用户数异常！");
        } else {
            return null;
        }
    }
}
