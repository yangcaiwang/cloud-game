package com.ycw.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.ycw.common.constant.Constants;
import com.ycw.common.exception.ServiceException;
import com.ycw.redis.service.RedisService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ycw.common.exception.enums.GlobalErrorCodeConstants.*;

/**
 * @Classname ImageCodeFilter
 * @Description 图形验证码过滤器
 */
@Component
@Order(-3)
public class ImageCodeFilter extends OncePerRequestFilter {
    @Autowired
    private RedisService redisService;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain) throws ServletException, IOException {
        // 获取code参数
        String code = obtainCode(request);
        // 必须为/login请求和post请求
        if (StrUtil.equals("/sys/login", request.getRequestURI()) && StrUtil.isAllNotEmpty(code) && StrUtil.equalsIgnoreCase(request.getMethod(), "POST")) {
            try {
                // 1. 进行验证码的校验
                validateCode(request);
            } catch (AuthenticationException e) {
                // 2. 如果校验不通过，调用SpringSecurity的校验失败处理器
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        // 3. 通过放行，进入下个过滤器
        chain.doFilter(request, response);
    }

    /**
     * 验证流程
     */
    private void validateCode(HttpServletRequest request) {

        String captcha = obtainCode(request);
        String key = obtainKey(request);
        // 验证验证码
        if (StrUtil.isBlank(captcha)) {
            throw new ServiceException(VALIDATE_CODE_NOT_NULL);
        }
        // 从redis中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = redisService.getCacheObject(Constants.IMAGE_KEY + key);
        if (kaptcha == null) {
            throw new ServiceException(VALIDATE_CODE_NOT);
        }
        if (!captcha.toLowerCase().equals(kaptcha)) {
            throw new ServiceException(VALIDATE_CODE_ERROR);
        }
    }

    /**
     * 获取前端传来的图片验证码
     */
    private String obtainCode(HttpServletRequest request) {
        String imageCode = "code";
        return request.getHeader(imageCode);
    }

    /**
     * 获取前端传来的图片验证码
     */
    private String obtainKey(HttpServletRequest request) {
        String token = "key";
        return request.getHeader(token);
    }

    /**
     * 失败处理器
     */
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
