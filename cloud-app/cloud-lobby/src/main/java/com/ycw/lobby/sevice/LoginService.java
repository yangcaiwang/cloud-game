package com.ycw.lobby.sevice;

import com.ycw.lobby.domain.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录校验方法
 *
 * @author ai-cloud
 */
public interface LoginService {

    /**
     * 账号密码登录
     *
     * @param password 密码
     * @param request  请求对象
     * @return 登录结果
     */
    public User login(Long userId);

    /**
     * 退出登录
     *
     * @return 结果
     */
    public void logout();
}
