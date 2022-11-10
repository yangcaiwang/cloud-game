package com.ycw.auth.ops.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ycw.auth.config.MyBeanUtil;
import com.ycw.common.AuthUserDetails;
import com.ycw.common.enums.LoginType;
import com.ycw.auth.ops.system.domain.SysUser;
import com.ycw.auth.ops.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * @Classname UserDetailsServiceImpl
 * @Description 用户身份验证
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserService sysUserService = MyBeanUtil.getBean(SysUserService.class);

    /**
     * 用户名密码登录
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        SysUser user = sysUserService.findSecurityUserByUser(sysUser);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：" + username + " 不存在.");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        System.out.println("加载中～～～～～～");
        Collection<? extends GrantedAuthority> authorities = getUserAuthorities(user.getUserId());
        return new AuthUserDetails(user.getUserId(), username, user.getPassword(), authorities, LoginType.USERNAME);
    }

    /**
     * 手机验证码登录
     */
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser();
        sysUser.setPhone(mobile);
        //  通过手机号mobile去数据库里查找用户以及用户权限
        SysUser user = sysUserService.findSecurityUserByUser(sysUser);
        if (ObjectUtil.isNull(user)) {
            log.info("登录手机号：" + mobile + " 不存在.");
            throw new UsernameNotFoundException("登录手机号：" + mobile + " 不存在");
        }
        // 获取用户拥有的角色
        Collection<? extends GrantedAuthority> authorities = getUserAuthorities(user.getUserId());
        return new AuthUserDetails(user.getUserId(), user.getUsername(), user.getPassword(), authorities, LoginType.SMS);
    }

    /**
     * 封装 根据用户Id获取权限
     */
    private Collection<? extends GrantedAuthority> getUserAuthorities(int userId) {
        // 获取用户拥有的角色
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        // 权限集合
        Set<String> permissions = sysUserService.findPermsByUserId(userId);
        // 角色集合
        Set<String> roleIds = sysUserService.findRoleIdByUserId(userId);
        permissions.addAll(roleIds);
        return AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));
    }
}
