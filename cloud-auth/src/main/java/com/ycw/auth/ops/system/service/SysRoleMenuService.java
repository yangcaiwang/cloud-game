package com.ycw.auth.ops.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.auth.ops.system.domain.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色id查询部门ids
     *
     * @param userId 用户id
     * @return 菜单id列表
     */
    List<Integer> getMenuIdByUserId(Integer userId);
}
