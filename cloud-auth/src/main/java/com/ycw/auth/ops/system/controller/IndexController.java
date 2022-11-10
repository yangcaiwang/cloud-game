package com.ycw.auth.ops.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.ycw.auth.ops.system.dto.LoginDto;
import com.ycw.auth.sms.AliYunSmsUtils;
import com.ycw.auth.sms.SmsCodeService;
import com.ycw.auth.sms.SmsResponse;
import com.ycw.common.constant.Constants;
import com.ycw.common.exception.ServiceException;
import com.ycw.common.response.R;
import com.ycw.auth.ops.system.dto.UserDto;
import com.ycw.auth.ops.system.service.SysUserService;
import com.ycw.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.ycw.common.exception.enums.GlobalErrorCodeConstants.VALIDATE_CODE_ERROR;
import static com.ycw.common.exception.enums.GlobalErrorCodeConstants.VALIDATE_CODE_NOT;

/**
 * <p>
 *
 * @Classname IndexController
 * @Description 主页 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/sys")
public class IndexController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SmsCodeService smsCodeService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/register")
    public R register(@RequestBody UserDto userDTO) {
        Object cacheObject = redisService.getCacheObject(userDTO.getPhone());
        if (ObjectUtil.isNull(cacheObject)) {
            throw new ServiceException(VALIDATE_CODE_NOT);
        }
        if (!userDTO.getSmsCode().toLowerCase().equals(cacheObject)) {
            throw new ServiceException(VALIDATE_CODE_ERROR);
        }
        return R.ok(userService.register(userDTO));
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginDto loginDTO) {
        String token = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        return R.ok(map);
    }

    /**
     * 登出
     * 前端清除token即可
     */
    @RequestMapping("/logout")
    public String logout() {
        return "success";
    }

    /**
     * 生成验证码
     */
    @GetMapping("/captcha.jpg")
    public R captcha() throws IOException {
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        String key = UUID.randomUUID().toString();
        redisService.setCacheObject(Constants.IMAGE_KEY + key, result, 2L, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>();
        map.put("key", key);
        map.put("img", captcha.toBase64());
        map.put("code", result);
        return R.ok(map);
    }

    /**
     * 发送短信验证码
     */
    @PostMapping("/sendCode/{phone}")
    public R sendSmsCode(@PathVariable("phone") String phone) {
        SmsResponse smsResponse = AliYunSmsUtils.sendSms(phone, "prex", "登录");

        if (ObjectUtil.isNull(smsResponse)) {
            return R.error("短信发送失败");
        }
        // 保存到验证码到 redis 有效期两分钟
        redisService.setCacheObject(phone, smsResponse.getSmsCode(), 2L, TimeUnit.MINUTES);
        return R.ok();
    }
}
