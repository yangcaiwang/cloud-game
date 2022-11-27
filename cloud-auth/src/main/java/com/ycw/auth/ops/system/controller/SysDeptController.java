package com.ycw.auth.ops.system.controller;

import com.ycw.auth.ops.system.domain.SysDept;
import com.ycw.auth.ops.system.dto.DeptDto;
import com.ycw.auth.ops.system.service.SysDeptService;
import com.ycw.common.response.R;
import com.ycw.operatelog.annotation.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * @Classname SysDeptController
 * @Description 部门管理 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {
    @Autowired
    private SysDeptService deptService;

    /**
     * 保存部门信息
     */
    @PostMapping
    @OperateLog(description = "保存部门信息")
    @PreAuthorize("hasAuthority('sys:dept:add')")
    public R save(@RequestBody SysDept sysDept) {
        return R.ok(deptService.save(sysDept));
    }

    /**
     * 获取部门信息
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:dept:view')")
    public R getDeptList() {
        return R.ok(deptService.selectDeptList());
    }

    /**
     * 获取部门树
     */
    @GetMapping("/tree")
    public R getDeptTree() {
        return R.ok(deptService.getDeptTree());
    }


    /**
     * 更新部门信息
     */
    @OperateLog(description = "更新部门信息")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:dept:update')")
    public R update(@RequestBody DeptDto deptDto) {
        return R.ok(deptService.updateDeptById(deptDto));
    }

    /**
     * 根据id删除部门信息
     */
    @OperateLog(description = "根据id删除部门信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    public R delete(@PathVariable("id") Integer id) {
        return R.ok(deptService.removeById(id));
    }
}

