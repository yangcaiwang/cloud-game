package com.springboot.app.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springboot.app.domain.entity.User;
import com.springboot.app.mapper.UserMapper;
import com.springboot.app.sevice.LoginService;
import com.springboot.common.base.exception.GlobalException;
import com.springboot.common.utils.ip.IpUtils;
import com.springboot.common.utils.sign.AuthUtils;
import com.springboot.datasource.annotation.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录校验方法
 *
 * @author ai-cloud
 */
@Slf4j
@Service
@DataSource
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 账号密码登录
     *
     * @param password 密码
     * @param request  请求对象
     * @return 登录结果
     */
    @Override
    public User login(Long userId, String password, HttpServletRequest request) {
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(userId != null, User::getUserId, userId);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            User newUser = new User();
            newUser.setUserId(userId);
            assert userId != null;
            newUser.setUsername(userId.toString());
            newUser.setPassword(AuthUtils.encryptPassword(password));
            newUser.setCoins(100000L);
            newUser.setIpaddr(IpUtils.getIpAddr(request));
            newUser.setNickName("user" + userId);
            userMapper.insert(newUser);
            user = newUser;
        }
        if (!AuthUtils.matchesPassword(password, user.getPassword())) {
            throw new GlobalException("用户不存在/密码错误");
        }
        return user;
    }

    /**
     * 微信授权登录
     *
     * @return 登录结果
     */
    @Override
    public User weixin() {
        return null;
    }

    @Override
    public void logout() {

    }
}
