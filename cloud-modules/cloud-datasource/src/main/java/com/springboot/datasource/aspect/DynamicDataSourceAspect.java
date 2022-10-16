package com.springboot.datasource.aspect;

import com.springboot.common.context.DynamicDataSourceContextHolder;
import com.springboot.datasource.annotation.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源切面配置类，用于获取方法和类上的注解，进行动态切换数据源
 */
@Aspect
@Component
@Order(-1) // 保证该AOP在@Transactional之前执行
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.springboot.datasource.annotation.DataSource)"
            + "|| @within(com.springboot.datasource.annotation.DataSource)")
    public void dsPointCut() {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method targetMethod = this.getTargetMethod(point);
        //获取要切换的数据源
        DataSource dataSource = targetMethod.getAnnotation(DataSource.class);
        if (dataSource != null) {
            System.err.println("正在使用的数据源是: "+dataSource.name());
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.name().name());
        } else {
            //看类上有没有注解
            Class<?> aClass = targetMethod.getDeclaringClass();
            dataSource = aClass.getAnnotation(DataSource.class);
            if (dataSource != null) {
                System.err.println("正在使用的数据源是:=> "+dataSource.name());
                DynamicDataSourceContextHolder.setDataSourceType(dataSource.name().name());
            }else {
                System.err.println("正在使用默认数据源");
            }
        }
        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.removeDataSourceType();
        }
    }

    /**
     * 获取目标方法
     */
    private Method getTargetMethod(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
//        return pjp.getTarget().getClass().getDeclaredMethod(agentMethod.getName(), agentMethod.getParameterTypes());
    }
}
