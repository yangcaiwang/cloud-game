package log.event;

import log.domain.Log;
import log.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Classname SysLogListener
 * @Description 注解形式的监听 异步监听日志事件
 */
@Slf4j
@Component
public class OperateLogListener {
    @Autowired
    private OperateLogService sysLogService;

    @Async
    @Order
    @EventListener(OperateLogEvent.class)
    public void saveSysLog(OperateLogEvent event) {
        Log sysLog = (Log) event.getSource();
        // 保存日志
        sysLogService.save(sysLog);
    }
}
