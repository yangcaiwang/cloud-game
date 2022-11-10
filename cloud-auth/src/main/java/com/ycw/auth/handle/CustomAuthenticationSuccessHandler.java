package com.ycw.auth.handle;

import com.ycw.common.AuthUserDetails;
import com.ycw.common.enums.LoginType;
import com.ycw.common.response.R;
import com.ycw.common.utils.JwtTokenUtil;
import com.ycw.common.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname CustomAuthenticationSuccessHandler
 * @Description 登录成功处理器
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUserDetails) {
            AuthUserDetails userDetail = (AuthUserDetails) authentication.getPrincipal();
            //存储认证信息
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成token
            String s = JwtTokenUtil.generateToken(userDetail);
            // 是短信登录返回token
            if (LoginType.SMS.equals(userDetail.getLoginType())) {
                AuthUtil.writeJavaScript(R.ok(s), response);
            }
        }
    }
}

