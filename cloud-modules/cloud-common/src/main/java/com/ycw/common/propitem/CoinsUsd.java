package com.ycw.common.propitem;

import com.ycw.common.enums.ItemTypeConfigEnum;

/**
 * 美金道具类
 */
public class CoinsUsd extends PropItemStoreStruct {
    private Long number;
    private ItemTypeConfigEnum itemTypeConfigEnum = ItemTypeConfigEnum.CoinsUsd;

    public static CoinsUsd buildPropItemStoreStruct(String value) {
        CoinsUsd coinsUsd = new CoinsUsd();
        coinsUsd.setNumber(Long.parseLong(value));
        return coinsUsd;
    }

    public CoinsUsd() {}

    public CoinsUsd(Long number, ItemTypeConfigEnum itemTypeConfigEnum) {
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