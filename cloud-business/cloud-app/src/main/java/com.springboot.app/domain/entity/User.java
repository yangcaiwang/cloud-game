package com.springboot.app.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.springboot.app.domain.entity.base.BaseEntity;
import lombok.Data;

/**
 * 用户信息
 *
 * @author ai-cloud
 */
@TableName("user")
@Data
public class User extends BaseEntity {

    /**
     * 用户名id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 金币
     */
    private Long coins;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    @TableLogic
    private boolean deleted;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;
}
