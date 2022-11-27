package com.ycw.auth.ops.system.controller;

import com.ycw.auth.util.AuthUserDetails;
import com.ycw.auth.util.AuthUtil;
import com.ycw.common.page.PageParam;
import com.ycw.common.response.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sys")
public class SysLogController {

    @Resource
    private com.ycw.operatelog.feignservice.OperateLogFeignService operateLogFeignService;

    /**
     * 分页查询日志列表
     */
    @GetMapping("/log")
    @PreAuthorize("hasAuthority('sys:log')")
    public R selectLog(@RequestParam("page") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageParam pageParam = new PageParam(pageNum, pageSize);
        AuthUserDetails authUserDetails = AuthUtil.getUser();
        return R.ok(operateLogFeignService.selectLogList(pageParam, authUserDetails.getUserId().longValue()));
    }
}