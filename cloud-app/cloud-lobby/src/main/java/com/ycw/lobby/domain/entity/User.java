package com.ycw.lobby.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.ycw.datasource.entity.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 * @Classname User
 * @Description 用户表
 * </p>
 */
@TableName("user")
@Data
public class User {

    /**
     * 用户名id
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 金币
     */
    private Long coins;

    @TableLogic
    private boolean deleted;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
}
