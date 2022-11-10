package com.ycw.gateway.filter;

import com.ycw.common.AuthUserDetails;
import com.ycw.common.constant.Constants;
import com.ycw.common.constant.SecurityConstants;
import com.ycw.common.constant.TokenConstants;
import com.ycw.common.enums.UserType;
import com.ycw.common.utils.JwtTokenUtil;
import com.ycw.common.utils.ServletUtil;
import com.ycw.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname AuthFilter
 * @Description 网关全局过滤器
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    /**
     * 排除过滤的 uri 地址，nacos自行添加
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // 白名单放行的接口
        List<String> list = Arrays.asList("/app/login/**", "/sys/login/**", "/sys/auth/**");
        // 跳过不需要验证的路径
        if (StringUtil.matches(url, list)) {
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StringUtil.isEmpty(token)) {
            return unauthorizedResponse(exchange, "token不能为空");
        }
        AuthUserDetails userFromToken = JwtTokenUtil.getUserFromToken((HttpServletRequest) request);
        assert userFromToken != null;
        UserType userType = userFromToken.getUserType();
        if (userType == UserType.APP_USER) {
            addHeader(mutate, SecurityConstants.DETAILS_USER_ID, userFromToken.getUserId());
        }
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtil.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return ServletUtil.webFluxResponseWriter(exchange.getResponse(), msg, Constants.UNAUTHORIZED);
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        // 如果前端设置了token前缀，则裁剪掉前缀
        if (StringUtil.isNotEmpty(token)) {
            assert token != null;
            if (token.startsWith(TokenConstants.PREFIX)) {
                token = token.replaceFirst(TokenConstants.PREFIX, StringUtil.EMPTY);
            }
        }
        return token;
    }

    @Override
    public int getOrder() {
        return -200;
    }
}