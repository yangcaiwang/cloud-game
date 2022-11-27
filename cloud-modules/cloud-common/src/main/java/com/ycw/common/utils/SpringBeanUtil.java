package com.ycw.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 问题：如果在@Autowired处理bean逻辑之前使用@Autowired 会导致空指针异常
 * 解决：使用SpringBeanUtil工具 从ApplicationContext中获取bean
 * 注意：其他类加载顺序需要在其之后 用@DependsOn("springBeanUtil")控制
 * <p>
 */
@Component("springBeanUtil")
public class SpringBeanUtil implements ApplicationContextAware {
    protected static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext app) throws BeansException {
        if (applicationContext == null) {
            applicationContext = app;
        }
    }

    /**
     * 通过类的class从容器中手动获取对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBeanName(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}
