package com.ycw.openfeign.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * @Classname OkhttpProperties
 * @Description OkHttpFeign 实体类
 * </p>
 */
@Data
@Component
@ConfigurationProperties(prefix = "feign.okhttp")
public class OkhttpProperties {
    private Long connectTimeout;
    private Long readTimeout;
}