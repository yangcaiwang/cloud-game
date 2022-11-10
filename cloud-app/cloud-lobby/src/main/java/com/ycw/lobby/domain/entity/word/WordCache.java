package com.ycw.lobby.domain.entity.word;

import com.ycw.common.propitem.PropItemStoreStruct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @desc 字独缓存数据
 * @property index 索引位置
 * @property word 单词
 * @property whetherHit 是否命中
 */
@Data
@AllArgsConstructor
public class WordCache {
    private List<WordStatus> wordList;
    private List<PropItemStoreStruct> propItemStoreStruct;

    @Data
    public static class WordStatus {
        private int index;
        private String word;
        private Boolean whetherHit;
    }
}
