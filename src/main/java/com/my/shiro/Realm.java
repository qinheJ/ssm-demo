package com.my.shiro;

import com.my.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author QinHe at 2019-07-31
 */
public class Realm extends AuthorizingRealm {


    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入了认证方法");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        //这里模拟登录，判定用户名和密码相同时为认证成功
        if (username.equalsIgnoreCase(password)) {
            //登录成功
            System.out.println("认证成功");
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return new SimpleAuthenticationInfo(user, password, getName());

        } else {
            System.out.println("用户名或者密码错误");
            throw new AuthenticationException("用户名或者密码错误");
        }
    }

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入了授权方法");
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username = user.getUsername();
        //username=smallVip,权限标识perm=smallVip
        //username=bigVip,权限标识perm=bigVip
        info.addStringPermission(username);
        //页面中,views/shiro_test/smallVip.jsp 可以被smallVip和bigVip访问
        //页面中,views/shiro_test/bigVip.jsp 只能被bigVip访问
        //配置在spring-shiro.xml中
        return info;
    }

}
