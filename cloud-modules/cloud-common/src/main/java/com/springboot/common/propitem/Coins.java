package com.springboot.common.propitem;

import com.springboot.common.enums.ItemTypeConfigEnum;

/**
 * 金币道具类
 */
public class Coins extends PropItemStoreStruct {
    private Long number;
    private ItemTypeConfigEnum itemTypeConfigEnum = ItemTypeConfigEnum.Coins;

    @Override
    public Coins buildPropItemStoreStruct(String value) {
        Coins coins = new Coins();
        coins.setNumber(Long.parseLong(value));
        return coins;
    }

    public Coins() {}

    public Coins(Long number, ItemTypeConfigEnum itemTypeConfigEnum) {
        this.number = number;
        this.itemTypeConfigEnum = itemTypeConfigEnum;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public ItemTypeConfigEnum getItemTypeConfigEnum() {
        return itemTypeConfigEnum;
    }

    public void setItemTypeConfigEnum(ItemTypeConfigEnum itemTypeConfigEnum) {
        this.itemTypeConfigEnum = itemTypeConfigEnum;
    }
}
