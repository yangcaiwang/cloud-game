package com.ycw.common.enums;

/**
 * <p>
 * @Classname
 * @Description
 * </p>
 */
public enum MailType {

    DEVELOPER(-1L, "开发者邮件");

    private final Long type;
    private final String description;

    MailType(Long type, String description) {
        this.type = type;
        this.description = description;
    }

    public Long getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
