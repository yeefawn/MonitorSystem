package com.yeewenfag.shiro;

import com.yeewenfag.domain.vo.UserVo;
import com.yeewenfag.service.ResourceService;
import com.yeewenfag.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    private static Logger logger = LogManager.getLogger("UserRealm.class");

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @Override
    // 授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户信息
        UserVo user = (UserVo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        String roleId = user.getRole().getId();
        // 根据角色id获取该角色下所有资源的permission
        List<String> permissions = null;
        try {
            permissions = resourceService.selectPermissionByRole(roleId);
        } catch (Exception e) {
            logger.error(e);
        }
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        authorizationInfo.addStringPermissions(permissions);

        return authorizationInfo;
    }

    @Override
    // 认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名
        String username = (String) token.getPrincipal();
        UserVo user = null;
        try {
            user = userService.selectByUsername(username);
        } catch (Exception e) {
            logger.error(e);
        }
        if (user == null) {
            throw new UnknownAccountException(); //没找到帐号
        }
        if (user.getLocked() == 0) {
            throw new LockedAccountException(); //帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getId()), // 盐
                getName()
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
