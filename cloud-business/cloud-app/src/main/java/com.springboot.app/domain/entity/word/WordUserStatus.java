package com.springboot.app.domain.entity.word;

import com.baomidou.mybatisplus.annotation.*;
import com.springboot.app.domain.typehandler.WordCacheToJson;
import com.springboot.app.domain.entity.base.BaseEntity;
import lombok.Data;

/**
 * @desc 字独用户状态
 * @property
 */
@Data
@TableName(value = "word_user_status", autoResultMap = true)
public class WordUserStatus extends BaseEntity {

    private Long userId;

    @TableField(typeHandler = WordCacheToJson.class)
    private WordCache cache;

    @TableLogic
    private boolean deleted;

    @Version
    private Long version;

}
