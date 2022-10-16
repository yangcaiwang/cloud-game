package com.springboot.app.controller;

import com.springboot.app.domain.entity.User;
import com.springboot.app.sevice.LoginService;
import com.springboot.app.sevice.TokenService;
import com.springboot.common.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Date 2022/9/8 5:34 PM
 */
@RestController
@RequestMapping("/app")
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginService loginService;

    /**
     * 账号密码登录
     *
     * @param param
     * @param request 请求对象
     * @return 登录结果
     */
    @PostMapping("login")
    //
    public R<?> login(@RequestBody LoginParam param, HttpServletRequest request) {
        // 用户登录
        User user = loginService.login(param.getUserId(), param.getPassword(), request);
        // 获取登录token
        return R.ok(tokenService.createToken(user), "登录成功");
    }

   static class LoginParam {
        // 用户id
        private Long userId;
        // 用户账号或手机号
        private String username;

        // 用户密码
        private String password;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
