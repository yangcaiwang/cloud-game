package com.ycw.auth.ops.system.dto;

import lombok.Data;

/**
 * <p>
 * @Classname LoginDto
 * @Description 登陆dto
 * </p>
 */
@Data
public class LoginDto {
    // 用户账号
    private String username;
    // 用户密码
    private String password;
}