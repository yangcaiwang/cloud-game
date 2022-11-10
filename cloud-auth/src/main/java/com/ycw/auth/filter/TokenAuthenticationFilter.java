package com.ycw.auth.filter;

import cn.hutool.core.util.ObjectUtil;
import com.ycw.common.AuthUserDetails;
import com.ycw.common.enums.UserType;
import com.ycw.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
/**
 * Token 过滤器，验证 token 的有效性
 * 验证通过后，获得登陆用户 信息，并加入到 Spring Security 上下文
 **/
@Slf4j
@Component
@Order(-1)
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain) throws ServletException, IOException {
        AuthUserDetails authUserDetails = JwtTokenUtil.getUserFromToken(request);
        if (ObjectUtil.isNotNull(authUserDetails)) {
            UserType userType = authUserDetails.getUserType();
            switch (userType) {
                case APP_USER:
                    break;
                case SYS_USER:
                    Collection<? extends GrantedAuthority> authorities = authUserDetails.getAuthorities();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserDetails, null, authorities);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    break;
            }
        }
        chain.doFilter(request, response);
    }
}
