package com.springboot.app.domain.entity.word;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.app.domain.entity.base.BaseEntity;
import com.springboot.app.domain.typehandler.JsonToList;
import com.springboot.app.domain.typehandler.JsonToMap;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @desc 字独配置表
 * @property wordContent 单词内容
 * @property wordReward 单词奖励
 */
@Data
@TableName(value = "word_config", autoResultMap = true)
public class WordConfig extends BaseEntity {
    @TableField(typeHandler = JsonToList.class)
    private List<Integer> wordContent;

    @TableField(typeHandler = JsonToMap.class)
    private Map<Long, String> wordReward;
}
