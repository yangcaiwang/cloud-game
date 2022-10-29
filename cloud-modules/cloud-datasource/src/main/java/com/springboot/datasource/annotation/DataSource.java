package com.springboot.datasource.annotation;

import com.springboot.common.enums.DataSourcesType;

import java.lang.annotation.*;

/**
 * 自定义数据源注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited//表示该注解可以被子类继承,注意,仅针对类,成员属性、方法并不受此注释的影响
public @interface DataSource {
    DataSourcesType name() default DataSourcesType.CORE;
}
