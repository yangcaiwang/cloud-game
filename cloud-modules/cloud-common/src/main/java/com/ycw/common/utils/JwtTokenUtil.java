package com.ycw.common.utils;

import com.ycw.common.enums.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.ycw.common.constant.Constants.*;

/**
 * @Classname JwtUtil
 * @Description JWT工具类
 */
@Log4j2
@Component
public class JwtTokenUtil {
    /**
     * 生成令牌
     */
    public static String generateToken(Long userId, String username, UserType userType) {
        Map<String, Object> claims = new HashMap<>(4);
        claims.put(USERID, userId);
        claims.put(USERNAME, username);
        claims.put(USER_TYPE, userType);
        claims.put(CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    /**
     * 获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token)) {
            token = token.substring(TOKEN_HEAD.length());
        }
        return token;
    }

    /**
     * 从令牌中获取用户id
     */
    public static Long getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (Long) claims.get(USERID);
    }

    /**
     * 从令牌中获取用户名
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (String) claims.get(USERNAME);
    }

    /**
     * 从令牌中获取用户类型
     */
    public static UserType getUserTypeFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (UserType) claims.get(USER_TYPE);
    }

    /**
     * 从令牌中获取数据声明
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 验证令牌
     */
    public static Boolean validateToken(String token, String username) {
        String userName = getUsernameFromToken(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }

    /**
     * 刷新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 判断令牌是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
