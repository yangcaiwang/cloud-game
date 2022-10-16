package com.springboot.app.sevice;

import com.springboot.app.domain.entity.User;

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
    public User login(Long userId, String password, HttpServletRequest request);

    /**
     * 微信授权登录
     *
     * @return 登录结果
     */
    public User weixin();

    /**
     * 退出登录
     *
     * @return 结果
     */
    public void logout();

}
