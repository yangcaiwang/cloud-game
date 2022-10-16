package com.springboot.common.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 道具类型
 */
public enum ItemTypeConfigEnum {
    Coins(101L),
    CoinsUsd(888L);

    private final long id;

    ItemTypeConfigEnum(long id) {
        this.id = id;
    }

    public static ItemTypeConfigEnum getById(Long id) {
        // 构造映射集合
        Map<Long, ItemTypeConfigEnum> map = Arrays.stream(values()).collect(Collectors.toMap(itemTypeConfigEnum -> itemTypeConfigEnum.id, itemTypeConfigEnum -> itemTypeConfigEnum));
        return map.get(id);
    }
}
