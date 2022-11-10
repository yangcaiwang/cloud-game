package log.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import log.domain.Log;
import log.mapper.OperateLogMapper;
import log.service.OperateLogService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, Log> implements OperateLogService {
    @Override
    public IPage<Log> selectLogList(Integer page, Integer pageSize, Integer type, String userName) {
        Page<Log> logPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Log> sysLogLambdaQueryWrapper = Wrappers.<Log>lambdaQuery().eq(Log::getType, type).orderByDesc(Log::getStartTime);
        if (StrUtil.isNotEmpty(userName)) {
            sysLogLambdaQueryWrapper.like(Log::getUserName, userName);
        }
        return baseMapper.selectPage(logPage, sysLogLambdaQueryWrapper);
    }
}
