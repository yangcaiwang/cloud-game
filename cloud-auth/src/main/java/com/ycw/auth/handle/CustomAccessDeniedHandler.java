package com.ycw.auth.handle;

import cn.hutool.http.HttpStatus;
import com.ycw.common.response.R;
import com.ycw.auth.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname CustomAccessDeniedHandler
 * @Description 用来解决匿名用户访问无权限资源时的异常
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        log.error("请求访问: " + request.getRequestURI() + " 接口， 没有访问权限");
        AuthUtil.writeJavaScript(R.error(HttpStatus.HTTP_UNAUTHORIZED, "请求访问:" + request.getRequestURI() + "接口,没有访问权限"), response);
    }
}
