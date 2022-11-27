package com.ycw.operatelog.event;

import com.ycw.operatelog.domain.Log;
import org.springframework.context.ApplicationEvent;

/**
 * @Classname SysLogEvent
 * @Description 系统日志事件
 */
public class OperateLogEvent extends ApplicationEvent {
    public OperateLogEvent(Log log) {
        super(log);
    }
}
