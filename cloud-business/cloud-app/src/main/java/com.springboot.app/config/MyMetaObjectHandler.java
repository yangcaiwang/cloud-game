package com.springboot.app.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 元对象处理程序新
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 配置插入时需要填充的字段
     * 1.支持 Date LocalDateTime
     * 2.不支持 Instant
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 插入数据填充插入时间
        this.strictInsertFill(metaObject, "createAt", LocalDateTime.class, LocalDateTime.now());
        // 插入数据填充更新时间
        this.strictInsertFill(metaObject, "updateAt", LocalDateTime.class, LocalDateTime.now());
        // 插入数据填充默认版本号为1
        this.strictInsertFill(metaObject, "version", Long.class, 1L);
        log.info("start insert fill...");
    }

    /**
     * 配置更新时需要填充的字段
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新数据填充更新时间
        this.strictUpdateFill(metaObject, "updateAt", LocalDateTime.class, LocalDateTime.now());
        log.info("start update fill...");
    }
}
