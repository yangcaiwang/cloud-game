package log.controller;

import com.ycw.common.response.R;
import log.annotation.OperateLog;
import log.service.OperateLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/log")
public class SysLogController {

    @Resource
    private OperateLogService logService;

    /**
     *
     * 分页查询日志列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:log:view')")
    public R selectLog(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, @RequestParam("type") Integer type, @RequestParam String userName){
        return R.ok(logService.selectLogList(page, pageSize,type,userName));
    }

    @OperateLog(description = "删除日志")
    @DeleteMapping("/{logId}")
    @PreAuthorize("hasAuthority('sys:log:delete')")
    public R delete(@PathVariable("logId") Integer logId) {
        return R.ok(logService.removeById(logId));
    }
}

