package com.ycw.datasource.annotation;

import com.ycw.common.enums.DataSourcesType;

import java.lang.annotation.*;

/**
 * 自定义数据源注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited//表示该注解可以被子类继承,注意,仅针对类,成员属性、方法并不受此注释的影响
public @interface DataSource {
    DataSourcesType name() default DataSourcesType.APP;
}
