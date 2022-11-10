package com.ycw.auth.ops.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ycw.auth.ops.system.domain.SysDict;
import com.ycw.auth.ops.system.dto.DictDto;
import com.ycw.auth.ops.system.service.SysDictService;
import com.ycw.common.response.R;
import log.annotation.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService dictService;

    /**
     * 添加字典信息
     */
    @OperateLog(description = "添加字典信息")
    @PreAuthorize("hasAuthority('sys:dict:add')")
    @PostMapping
    public R add(@RequestBody SysDict sysDict) {
        return R.ok(dictService.save(sysDict));
    }

    /**
     * 获取字典列表集合
     */
    @OperateLog(description = "查询字典集合")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:dipt:view')")
    public R getList(Page page, SysDict sysDict) {
        return R.ok(dictService.page(page, Wrappers.query(sysDict)));
    }

    /**
     * 更新字典
     */
    @OperateLog(description = "更新字典")
    @PreAuthorize("hasAuthority('sys:dict:edit')")
    @PutMapping
    public R update(@RequestBody DictDto dictDto) {
        return R.ok(dictService.updateDict(dictDto));
    }

    /**
     * 根据id删除字典
     */
    @OperateLog(description = "根据id删除字典")
    @PreAuthorize("hasAuthority('sys:dict:del')")
    @DeleteMapping("{id}")
    public R delete(@PathVariable("id") int id) {
        return R.ok(dictService.removeById(id));
    }

    /**
     * 根据字典名称查询字段详情
     */
    @GetMapping("/queryDictItemByDictName/{dictName}")
    public R queryDictItemByDictName(@PathVariable("dictName") String dictName) {
        return R.ok(dictService.queryDictItemByDictName(dictName));
    }
}

