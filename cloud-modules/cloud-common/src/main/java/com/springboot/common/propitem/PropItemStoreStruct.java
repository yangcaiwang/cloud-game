package com.springboot.common.propitem;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.springboot.common.enums.ItemTypeConfigEnum;

/**
 * 游戏道具数据结构
 * <p>
 * 采用抽象工厂模式 好处：1.所有道具类实例对象统管一理，提高代码复用性; 2.提高代码可读性和拓展性
 * <p>
 * 两种道具生成方式
 * 1.抽象方法：继承抽象道具类的具体道具类，只需要传入道具数量生成对应道具结构
 * 2.静态方法：抽象道具类，需要传入道具id，道具数量，生成对应道具结构
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS) // 抽象类生成实例对象
abstract public class PropItemStoreStruct {
    public PropItemStoreStruct() {
    }

    abstract PropItemStoreStruct buildPropItemStoreStruct(String value);

    public static PropItemStoreStruct buildPropItemStoreStruct(Long itemTypeId, String itemTypeValue) {
        PropItemStoreStruct propItemStoreStruct = null;
        ItemTypeConfigEnum itemTypeConfigEnum = ItemTypeConfigEnum.getById(itemTypeId);
        switch (itemTypeConfigEnum) {
            case Coins:
                propItemStoreStruct = new Coins().buildPropItemStoreStruct(itemTypeValue);
                break;
            case CoinsUsd:
                propItemStoreStruct = new CoinsUsd().buildPropItemStoreStruct(itemTypeValue);
                break;
        }
        return propItemStoreStruct;
    }
}
