package com.ycw.auth.ops.system.controller;

import com.ycw.auth.ops.system.domain.SysMenu;
import com.ycw.auth.ops.system.dto.MenuDto;
import com.ycw.auth.ops.system.service.SysMenuService;
import com.ycw.auth.ops.system.util.PreUtil;
import com.ycw.common.AuthUserDetails;
import com.ycw.common.response.R;
import com.ycw.common.utils.AuthUtil;
import log.annotation.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * @Classname SysMenuController
 * @Description 菜单权限表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService menuService;

    /**
     * 添加菜单
     */
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @OperateLog(description = "添加菜单")
    @PostMapping
    public R save(@RequestBody SysMenu menu) {
        return R.ok(menuService.save(menu));
    }

    /**
     * 获取菜单树
     */
    @GetMapping
    public R getMenuTree() {
        AuthUserDetails authUserDetails = AuthUtil.getUser();
        return R.ok(menuService.selectMenuTree(authUserDetails.getUserId()));
    }

    /**
     * 获取所有菜单
     */
    @GetMapping("/getMenus")
    public R getMenus() {
        return R.ok(menuService.selectMenuTree(0));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("hasAuthority('sys:menu:update')")
    @OperateLog(description = "修改菜单")
    @PutMapping
    public R updateMenu(@RequestBody MenuDto menuDto) {
        return R.ok(menuService.updateMenuById(menuDto));
    }

    /**
     * 根据id删除菜单
     */
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @OperateLog(description = "删除菜单")
    @DeleteMapping("/{id}")
    public R deleteMenu(@PathVariable("id") Integer id) {
        return menuService.removeMenuById(id);
    }

    /**
     * 获取路由
     */
    @GetMapping("/getRouters")
    public R getRouters() {
        AuthUserDetails authUserDetails = AuthUtil.getUser();
//        List<MenuVo> menuVos = PreUtil.buildMenus(menuService.selectMenuTree(securityUser.getUserId()));
        return R.ok(PreUtil.buildMenus(menuService.selectMenuTree(authUserDetails.getUserId())));
    }
}

