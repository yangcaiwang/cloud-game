package com.ycw.lobby.controller;

import com.ycw.common.response.R;
import com.ycw.common.utils.ServletUtil;
import com.ycw.lobby.domain.entity.User;
import com.ycw.lobby.sevice.LoginService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname LoginController
 * @Description 登陆
 */
@Slf4j
@RestController
@RequestMapping("/app")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    //
    public R login() {
        Long userId = ServletUtil.getUserId();
        // 用户登录
        User user = loginService.login(userId);
        // 获取登录token
        return R.ok();
    }
}
