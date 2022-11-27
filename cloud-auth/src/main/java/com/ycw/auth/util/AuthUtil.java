package com.ycw.auth.util;

import com.alibaba.fastjson.JSON;
import com.ycw.common.constant.Constants;
import com.ycw.common.constant.TokenConstants;
import com.ycw.common.context.AuthContextHolder;
import com.ycw.common.exception.ServiceException;
import com.ycw.common.response.R;
import com.ycw.common.utils.ServletUtil;
import com.ycw.common.utils.StringUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static com.ycw.common.exception.enums.GlobalErrorCodeConstants.LOGIN_EXPIRED;

/**
 * 用户获取工具类
 *
 * @author ai-cloud
 */
public class AuthUtil {
    /**
     * 获取用户ID
     */
    public static String getUserId() {
        return AuthContextHolder.getUserId();
    }

    /**
     * 获取请求token
     */
    public static String getToken() {
        return getToken(Objects.requireNonNull(ServletUtil.getRequest()));
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        // 从header获取token标识
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 裁剪token前缀
     */
    public static String replaceTokenPrefix(String token) {
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtil.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
        }
        return token;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public static void writeJavaScript(R r, HttpServletResponse response) throws IOException {
        response.setStatus(Constants.SUCCESS);
        response.setCharacterEncoding(Constants.UTF8);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JSON.toJSONString(r));
        printWriter.flush();
    }

    /**
     * 获取用户
     */
    public static AuthUserDetails getUser(){
        try {
            return (AuthUserDetails) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ServiceException(LOGIN_EXPIRED);
        }
    }
    /**
     * 获取Authentication
     */
    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
