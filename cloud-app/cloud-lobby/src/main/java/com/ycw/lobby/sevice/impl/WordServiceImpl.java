package com.ycw.lobby.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ycw.common.enums.DataSourcesType;
import com.ycw.lobby.domain.entity.word.WordCache;
import com.ycw.lobby.domain.entity.word.WordConfig;
import com.ycw.lobby.domain.entity.word.WordUserStatus;
import com.ycw.lobby.mapper.WordConfigMapper;
import com.ycw.lobby.mapper.WordUserStatusMapper;
import com.ycw.lobby.sevice.WordService;
import com.ycw.common.propitem.PropItemStoreStruct;
import com.ycw.datasource.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@DataSource(name = DataSourcesType.APP)
public class WordServiceImpl implements WordService {

    @Autowired
    private WordConfigMapper wordConfigMapper;

    @Autowired
    private WordUserStatusMapper wordUserStatusMapper;

    @Override
    public WordServiceResponse init(WordServiceRequest request) {
        Long userId = request.getUserId();
        // 获取当前用户状态
        QueryWrapper<WordUserStatus> wordUserStatusQueryWrapper = new QueryWrapper<>();
        wordUserStatusQueryWrapper.ge("user_id", userId);
        WordUserStatus wordUserStatus = wordUserStatusMapper.selectOne(wordUserStatusQueryWrapper);
        if (wordUserStatus == null) {
            // 获取当前用户配置
            QueryWrapper<WordConfig> wordConfigQueryWrapper = new QueryWrapper<>();
            wordConfigQueryWrapper.ge("id", 1);
            WordConfig wordConfig = wordConfigMapper.selectOne(wordConfigQueryWrapper);
            List<Integer> worContentList = wordConfig.getWordContent();
            List<String> wordMapping = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                    "U", "V", "W", "X", "Y", "Z");
            List<WordCache.WordStatus> wordStatusList = worContentList.stream().map(i -> {
                String word = wordMapping.get(i);
                WordCache.WordStatus wordStatus = new WordCache.WordStatus();
                wordStatus.setWord(word);
                wordStatus.setIndex(i);
                wordStatus.setWhetherHit(false);
                return wordStatus;
            }).collect(Collectors.toList());
            Map<Long, String> mapLongStr = wordConfig.getWordReward();
            List<PropItemStoreStruct> propItemStoreStructList = mapLongStr.entrySet().stream().map(entry -> PropItemStoreStruct.buildPropItemStoreStruct(entry.getKey(), entry.getValue())).collect(Collectors.toList());
            WordUserStatus newWordUserStatus = new WordUserStatus();
            newWordUserStatus.setUserId(1L);
            newWordUserStatus.setCache(new WordCache(wordStatusList, propItemStoreStructList));
            wordUserStatusMapper.insert(newWordUserStatus);
            wordUserStatus = newWordUserStatus;
        }
        return new WordServiceResponse(wordUserStatus);
    }
}
