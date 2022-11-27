package com.ycw.auth.filter;

import cn.hutool.core.util.ObjectUtil;
import com.ycw.auth.util.AuthUserDetails;
import com.ycw.common.utils.JwtTokenUtil;
import com.ycw.redis.service.RedisService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.ycw.common.constant.Constants.*;

/**
 * Token 过滤器，验证 token 的有效性
 * 验证通过后，获得登陆用户 信息，并加入到 Spring Security 上下文
 **/
@Slf4j
@Component
@Order(-1)
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private RedisService redisService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain) throws ServletException, IOException {
        // 获取请求携带的令牌
        String token = JwtTokenUtil.getToken(request);
        // 解析AuthUserDetails
        AuthUserDetails authUserDetails = getAuthUserDetailsFromToken(token);
        if (ObjectUtil.isNotNull(authUserDetails)) {
            Collection<? extends GrantedAuthority> authorities = authUserDetails.getAuthorities();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserDetails, null, authorities);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }

    /**
     * 根据请求令牌获取登录认证信息
     *
     * @return 用户名
     */
    public AuthUserDetails getAuthUserDetailsFromToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = JwtTokenUtil.getClaimsFromToken(token);
            if (claims == null) {
                return null;
            }
            String username = (String) claims.get(USERNAME);
            if (username == null) {
                return null;
            }
            if (JwtTokenUtil.isTokenExpired(token)) {
                return null;
            }
            // 从token中解析userId
            Integer userId = (Integer) claims.get(USERID);
            // 从redis中解析权限列表
            Object authors = redisService.getCacheObject(AUTHORITIES + userId);
            Set<String> perms = new HashSet<>();
            if (authors instanceof List) {
                for (Object object : (List) authors) {
                    perms.add(((Map) object).get("authority").toString());
                }
            }
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(perms.toArray(new String[0]));
            if (JwtTokenUtil.validateToken(token, username)) {
                // 未把密码放到jwt
                return new AuthUserDetails(userId, username, "", authorities, null);
            }
        }
        return null;
    }
}
