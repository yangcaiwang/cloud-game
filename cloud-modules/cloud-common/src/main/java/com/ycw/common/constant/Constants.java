package com.ycw.common.constant;

public class Constants {
    //===========================返回状态码常量========================
    /**
     * 成功标记
     */
    public static final int SUCCESS = 200;

    /**
     * 失败标记
     */
    public static final int FAIL = 500;

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 邮件
     */
    public static final String RESET_MAIL = "MAIL";

    /**
     * 验证码
     */
    public static final String IMAGE_KEY = "IMAGE:KEY:";


    //===========================权限相关常量=============
    /**
     * 用户ID字段
     */
    public static final String USERID = "userId";

    /**
     * 用户名字段
     */
    public static final String USERNAME = "username";

    /**
     * 用户名字段
     */
    public static final String USER_TYPE = "userType";

    /**
     * 授权信息字段
     */
    public static final String AUTHORIZATION = "authorization";

    /**
     * 权限列表
     */
    public static final String AUTHORITIES = "authorities";

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final int FORBIDDEN = 403;


    //===========================jwt相关常量=============

    /**
     * 创建时间
     */
    public static final String CREATED = "created";

    /**
     * 密钥
     */
    public static final String SECRET = "abcdefgh";

    /**
     * 有效期1小时
     */
    public static final long EXPIRE_TIME = 60 * 60 * 1000;

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_HEAD = "Bearer ";
}
