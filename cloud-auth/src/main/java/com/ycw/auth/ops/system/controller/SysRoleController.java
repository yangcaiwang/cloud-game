package com.ycw.auth.ops.system.controller;

import com.ycw.auth.ops.system.dto.RoleDto;
import com.ycw.auth.ops.system.service.SysRoleService;
import com.ycw.common.response.R;
import log.annotation.OperateLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * @Classname SysRoleController
 * @Description 角色管理 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Resource
    private SysRoleService roleService;

    /**
     * 获取角色列表
     */
    @GetMapping
    @PreAuthorize("hasAuthority('sys:role:view')")
    public R getRoleList(@RequestParam String roleName) {
        return R.ok(roleService.selectRoleList(roleName));
    }

    /**
     * 保存角色以及菜单权限
     */
    @OperateLog(description = "保存角色以及菜单权限")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:add')")
    public R save(@RequestBody RoleDto roleDto) {
        return R.ok(roleService.saveRoleMenu(roleDto));
    }

    /**
     * 根据角色id获取菜单
     */
    @OperateLog(description = "据角色id获取菜单")
    @GetMapping("/findRoleMenus/{roleId}")
    public R findRoleMenus(@PathVariable("roleId") Integer roleId) {
        return R.ok(roleService.findMenuListByRoleId(roleId));
    }

    /**
     * 更新角色以及菜单权限
     */
    @OperateLog(description = "更新角色以及菜单权限")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:role:update')")
    public R update(@RequestBody RoleDto roleDto) {
        return R.ok(roleService.updateRoleMenu(roleDto));
    }

    /**
     * 删除角色以及权限
     */
    @OperateLog(description = "删除角色以及权限")
    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public R delete(@PathVariable("roleId") Integer roleId) {
        return R.ok(roleService.removeById(roleId));
    }
}

