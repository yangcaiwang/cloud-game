package log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import log.domain.Log;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 */
public interface OperateLogService extends IService<Log> {
    /**
     * 分页查询日志
     * @param page
     * @param pageSize
     * @param type
     * @return
     */
    IPage<Log> selectLogList(Integer page, Integer pageSize, Integer type, String userName);
}
