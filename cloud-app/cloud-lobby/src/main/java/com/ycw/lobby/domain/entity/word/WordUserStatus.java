package com.ycw.lobby.domain.entity.word;

import com.baomidou.mybatisplus.annotation.*;
import com.ycw.datasource.entity.BaseEntity;
import com.ycw.lobby.domain.typehandler.WordCacheToJson;
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
