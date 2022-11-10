package com.ycw.auth.sms;

import java.util.Map;

/**
 * @Classname SmsCodeService
 * @Description 短信服务
 */
public interface SmsCodeService {

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    Map<String, Object> sendCode(String phone);
}
