package com.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pojo.User;
import com.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ASUS
 */ //提供shiro与应用程序之间交互的组件:

public class MyRealm extends AuthorizingRealm {

    //service层注入:
    @Autowired
    private IUserService userService;


    //授权:  返回当前登录用户对应的权限.
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.识别当前用户id.
        User primaryPrincipal = (User)principalCollection.getPrimaryPrincipal();
        long id = primaryPrincipal.getId();

        //2.根据id查询角色:  admin
       //Role role1 = this.roleService.queryRolesByUid( id );

        //根据id查询权限:  bill:*
        //List<Permission> permList = this.permService.queryPermsByUid( id );

        //3.准备返回角色:
        SimpleAuthorizationInfo zation = new SimpleAuthorizationInfo();

        //返回一个角色名称:
        zation.addRole("employ");

        //返回一个角色名称集合:
//        List<String> rolesName = new ArrayList();
//        rolesName.add("admin");
//        rolesName.add("employ");
//        zation.addRoles(rolesName);

        //返回一个权限:
        zation.addStringPermission("bill:*");

//        List<String> permNames = new ArrayList();
//        permNames.add("bill:*");
//        permNames.add("bill:query");
//        zation.addStringPermissions( permNames );

        return zation;
    }


    //就是认证身份的实现代码:
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //封装了一个token并提交:
        Object name = authenticationToken.getPrincipal();

        //1.检查账号是否存在.
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username" , name);

        User u1 = this.userService.getOne(wrapper);


        //2.比对密码. 借助系统的加密方式完成加密后, 才能比对.   输入密码 + 加密   ==    数据库的加密密码
        //3.存储身份对象.

        //绑定参数: Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName
        //       1.存储的对象.   2. 加密密码.   3.盐值.   4.当前realm组件的名称.
        SimpleAuthenticationInfo cationinfo = new SimpleAuthenticationInfo(u1 , u1.getUserPassword() , ByteSource.Util.bytes( u1.getSaltVal() ) , getName());
        return cationinfo;
    }
}
