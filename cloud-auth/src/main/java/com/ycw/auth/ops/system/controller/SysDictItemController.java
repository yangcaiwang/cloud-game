package com.ycw.auth.ops.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ycw.auth.ops.system.domain.SysDictItem;
import com.ycw.auth.ops.system.service.SysDictItemService;
import com.ycw.common.response.R;
import com.ycw.operatelog.annotation.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * @Classname SysDictItemController
 * @Description 字典详情 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sys/dictItem")
public class SysDictItemController {
    @Autowired
    private SysDictItemService dictItemService;

    /**
     * 分页查询字典详情内容
     */
    @OperateLog(description = "查询字典详情集合")
    @GetMapping
    public R getDictItemPage(Page page, SysDictItem sysDictItem) {
        return R.ok(dictItemService.page(page, Wrappers.query(sysDictItem)));
    }

    /**
     * 添加字典详情
     */
    @OperateLog(description = "添加字典详情")
    @PreAuthorize("hasAuthority('sys:dictItem:add')")
    @PostMapping
    public R add(@RequestBody SysDictItem sysDictItem) {
        return R.ok(dictItemService.save(sysDictItem));
    }

    /**
     * 更新字典详情
     */
    @OperateLog(description = "更新字典详情")
    @PreAuthorize("hasAuthority('sys:dictItem:edit')")
    @PutMapping
    public R update(@RequestBody SysDictItem sysDictItem) {
        return R.ok(dictItemService.updateById(sysDictItem));
    }

    /**
     * 删除字典详情
     */
    @OperateLog(description = "删除字典详情")
    @PreAuthorize("hasAuthority('sys:dictItem:del')")
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String id) {
        return R.ok(dictItemService.removeById(id));
    }
}
