package com.springboot.app.sevice;

import com.springboot.app.domain.entity.User;
import com.springboot.common.constant.SecurityConstants;
import com.springboot.common.utils.JwtUtils;
import com.springboot.common.utils.ServletUtils;
import com.springboot.common.utils.ip.IpUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * token验证处理
 *
 * @author ai-cloud
 */
@Component
public class TokenService {

    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(User user) {
        Long userId = user.getUserId();
        String userName = user.getUserName();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);

        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("token", JwtUtils.createToken(claimsMap));
        rspMap.put("userInfo", user);
        return rspMap;
    }
}