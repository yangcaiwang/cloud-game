package com.ycw.auth.filter;

import cn.hutool.core.util.ObjectUtil;
import com.ycw.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.ycw.common.exception.enums.GlobalErrorCodeConstants.*;

/**
 * @Classname SmsCodeFilter
 * @Description 短信验证码过滤器
 */
@Component
@Order(-2)
public class SmsCodeFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private final Set<String> urls = new HashSet<>();

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        // 这里配置需要拦截的地址
        urls.add("/mobile/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls)
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
                break;
            }
        if (action) {
            try {
                validate(request);
            } catch (AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) {
        //短信验证码
        String smsCode = obtainSmsCode(request);
        // 手机号
        String mobile = obtainMobile(request);
        Object redisCode = redisTemplate.opsForValue().get(mobile);
        if (smsCode == null || smsCode.isEmpty()) {
            throw new ServiceException(VALIDATE_CODE_NOT_NULL);
        }
        if (ObjectUtil.isNull(redisCode)) {
            throw new ServiceException(VALIDATE_CODE_NOT);
        }
        if (!smsCode.toLowerCase().equals(redisCode)) {
            throw new ServiceException(VALIDATE_CODE_ERROR);
        }
    }

    /**
     * 获取验证码
     */
    private String obtainSmsCode(HttpServletRequest request) {
        return request.getParameter("smsCode");
    }

    /**
     * 获取手机号
     */
    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter("phone");
    }
}
