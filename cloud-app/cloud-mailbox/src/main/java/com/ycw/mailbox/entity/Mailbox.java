package com.ycw.mailbox.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;

/**
 * <p>
 *
 * @Classname MailBox
 * @Description 邮件表
 * </p>
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Mailbox implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @MongoId
    private ObjectId id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 文本
     */
    private String body;

    /**
     * 附件
     */
    private String appendix;

    /**
     * 邮件类型
     */
    private Long mailType;

    /**
     * 邮件描述
     */
    private String mailTypeDescription;

    /**
     * 领取时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant collectAt;

    /**
     * 过期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant expiredAt;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant createAt;
}
