package com.springboot.app.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.app.domain.entity.User;
import com.springboot.app.mapper.UserMapper;
import com.springboot.app.sevice.LoginService;
import com.springboot.common.base.exception.GlobalException;
import com.springboot.common.utils.ip.IpUtils;
import com.springboot.common.utils.sign.AuthUtils;
import com.springboot.datasource.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

/**
 * 登录校验方法
 *
 * @author ai-cloud
 */
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
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.ge("user_id", userId);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            User newUser = new User();
            newUser.setUserId(userId);
            newUser.setUserName(userId.toString());
            newUser.setPassword(AuthUtils.encryptPassword("ycw123129"));
            newUser.setCoins(100000L);
            newUser.setIpaddr(IpUtils.getIpAddr(request));
            newUser.setNickName(userId.toString());
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
