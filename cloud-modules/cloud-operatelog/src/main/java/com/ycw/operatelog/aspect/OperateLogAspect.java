package com.ycw.operatelog.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgentUtil;
import com.ycw.common.enums.UserType;
import com.ycw.common.response.R;
import com.ycw.common.utils.IPUtil;
import com.ycw.common.utils.JwtTokenUtil;
import com.ycw.operatelog.domain.Log;
import com.ycw.operatelog.event.OperateLogEvent;
import com.ycw.operatelog.util.OperateLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Classname SysLogAspect
 * @Description 系统日志切面
 * ①切面注解得到请求数据 -> ②发布监听事件 -> ③异步监听日志入库
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    private final ThreadLocal<Log> sysLogThreadLocal = new ThreadLocal<>();

    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(com.ycw.operatelog.annotation.OperateLog)")
    public void sysLogAspect() {
    }

    /**
     * 拦截控制层的操作日志
     */
    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        Log log = new Log();
        //将当前实体保存到threadLocal
        sysLogThreadLocal.set(log);
        // 开始时间
        long beginTime = Instant.now().toEpochMilli();
        HttpServletRequest request = com.ycw.common.utils.ServletUtil.getRequest();
        String token = JwtTokenUtil.getToken(request);
        UserType userType = JwtTokenUtil.getUserTypeFromToken(token);
        String username;
        if (userType == UserType.APP_USER) {
            Long userId = JwtTokenUtil.getUserIdFromToken(token);
            username = userId.toString();
        } else {
            username = JwtTokenUtil.getUsernameFromToken(token);
        }
        log.setUserName(username);
        log.setActionUrl(URLUtil.getPath(request.getRequestURI()));
        log.setStartTime(LocalDateTime.now());
        String ip = ServletUtil.getClientIP(request);
        log.setIp(ip);
        log.setLocation(IPUtil.getCityInfo(ip));
        log.setRequestMethod(request.getMethod());
        String uaStr = request.getHeader("user-agent");
        log.setBrowser(UserAgentUtil.parse(uaStr).getBrowser().toString());
        log.setOs(UserAgentUtil.parse(uaStr).getOs().toString());

        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        //获取执行的方法名
        log.setActionMethod(joinPoint.getSignature().getName());
        // 类名
        log.setClassPath(joinPoint.getTarget().getClass().getName());
        log.setActionMethod(joinPoint.getSignature().getName());
        log.setFinishTime(LocalDateTime.now());
        // 参数
        log.setParams(Arrays.toString(args));
        log.setDescription(OperateLogUtil.getControllerMethodDescription(joinPoint));
        long endTime = Instant.now().toEpochMilli();
        log.setConsumingTime(endTime - beginTime);
    }

    /**
     * 返回通知
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret) {
        //得到当前线程的log对象
        Log log = sysLogThreadLocal.get();
        // 处理完请求，返回内容
        R r = Convert.convert(R.class, ret);
        if (r.getCode() == 200) {
            // 正常返回
            log.setType(1);
        } else {
            log.setType(2);
            log.setExDetail(r.getMsg());
        }
        // 发布事件
        applicationContext.publishEvent(new OperateLogEvent(log));
        //移除当前log实体
        sysLogThreadLocal.remove();
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public void doAfterThrowable(Throwable e) {
        Log log = sysLogThreadLocal.get();
        // 异常
        log.setType(2);
        // 异常对象
        log.setExDetail(OperateLogUtil.getStackTrace(e));
        // 异常信息
        log.setExDesc(e.getMessage());
        // 发布事件
        applicationContext.publishEvent(new OperateLogEvent(log));
        //移除当前log实体
        sysLogThreadLocal.remove();
    }
}
