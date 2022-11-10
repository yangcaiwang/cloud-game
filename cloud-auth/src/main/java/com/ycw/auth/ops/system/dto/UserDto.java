package com.ycw.auth.ops.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * @Classname UserDto
 * @Description 用户Dto
 * </p>
 */
@Data
public class UserDto implements Serializable {

    private Integer userId;
    private String username;
    private String password;
    private Integer deptId;
    private String phone;
    private String email;
    private String avatar;
    private String lockFlag;
    private String delFlag;
    private List<Integer> roleList;
    private List<Integer> deptList;
    /**
     * 新密码
     */
    private String newPassword;
    private String smsCode;
}
