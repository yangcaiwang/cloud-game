package com.ycw.common.exception.enums;

import com.ycw.common.exception.ErrorCode;

/**
 * 全局错误码枚举
 * 0-999 系统异常编码保留
 *
 * 一般情况下，使用 HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 * 虽然说，HTTP 响应状态码作为业务使用表达能力偏弱，但是使用在系统层面还是非常不错的
 * 比较特殊的是，因为之前一直使用 0 作为成功，就不使用 200 啦。
 */
public interface GlobalErrorCodeConstants {

    // ========== 客户端错误段 ==========
    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode LOGIN_EXPIRED = new ErrorCode(402, "登录状态过期");
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(404, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "请求方法不正确");
    ErrorCode LOCKED = new ErrorCode(423, "请求失败，请稍后重试"); // 并发请求，不允许
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "请求过于频繁，请稍后重试");
    ErrorCode VALIDATE_CODE_NOT_NULL = new ErrorCode(430, "验证码不能为空");
    ErrorCode VALIDATE_CODE_NOT = new ErrorCode(431, "验证码已失效");
    ErrorCode VALIDATE_CODE_ERROR = new ErrorCode(432, "验证码错误");
    ErrorCode VALIDATE_PASSWORD_ERROR = new ErrorCode(433, "密码错误");
    ErrorCode VALIDATE_ORIGINAL_PASSWORD_ERROR = new ErrorCode(434, "原密码错误");
    ErrorCode VALIDATE_ORIGINAL_NEW_PASSWORD_NOT = new ErrorCode(435, "新密码不能与旧密码相同");
    ErrorCode VALIDATE_USERNAME_ALREADY_REGISTER = new ErrorCode(435, "账户名已被注册");
    ErrorCode VALIDATE_PHONE_ALREADY_REGISTER = new ErrorCode(435, "手机号已被注册");

    // ========== 服务端错误段 ==========
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");
    ErrorCode INTERNAL_PARENT_MENU_NOT_CAN_LIST_TYPE = new ErrorCode(501, "上级菜单只能为目录类型");
    ErrorCode INTERNAL_PARENT_MENU_ONLY_CAN_MENU_TYPE = new ErrorCode(502, "上级菜单只能为目录类型");

    // ========== 自定义错误段 ==========
    ErrorCode REPEATED_REQUESTS = new ErrorCode(900, "重复请求，请稍后重试"); // 重复请求
    ErrorCode DEMO_DENY = new ErrorCode(901, "演示模式，禁止写操作");
    ErrorCode UNKNOWN = new ErrorCode(999, "未知错误");

    /**
     * 是否为服务端错误，参考 HTTP 5XX 错误码段
     *
     * @param code 错误码
     * @return 是否
     */
   static boolean isServerErrorCode(Integer code) {
       return code != null
               && code >= INTERNAL_SERVER_ERROR.getCode() && code <= INTERNAL_SERVER_ERROR.getCode() + 99;
   }
}
