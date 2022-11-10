package com.ycw.lobby.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ycw.common.enums.DataSourcesType;
import com.ycw.lobby.domain.entity.User;
import com.ycw.lobby.mapper.UserMapper;
import com.ycw.lobby.sevice.LoginService;
import com.ycw.datasource.annotation.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录校验方法
 *
 * @author ai-cloud
 */
@Slf4j
@Service
@DataSource(name = DataSourcesType.APP)
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 登陆
     *
     * @param userId 用户ID
     * @return 登录结果
     */
    @Override
    public User login(Long userId) {
        LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
        userQueryWrapper.eq(userId != null, User::getUserId, userId);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            User newUser = new User();
            newUser.setUserId(userId);
            assert userId != null;
            newUser.setCoins(100000L);
            newUser.setNickName("user" + userId);
            userMapper.insert(newUser);
            user = newUser;
        }
        return user;
    }

    @Override
    public void logout() {
    }
}
