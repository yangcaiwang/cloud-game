package com.ycw.auth.ops.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.auth.ops.system.domain.SysMenu;
import com.ycw.auth.ops.system.domain.SysRole;
import com.ycw.auth.ops.system.dto.RoleDto;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 保存角色和菜单
     *
     * @param roleDto 角色Dto
     * @return 是否保存成功
     */
    boolean saveRoleMenu(RoleDto roleDto);

    /**
     * 更新角色和菜单
     *
     * @param roleDto 角色Dto
     * @return 是否更新成功
     */
    boolean updateRoleMenu(RoleDto roleDto);

    /**
     * 根据主键，删除角色
     *
     * @param id 主键id
     * @return 是否删除成功
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 通过角色名称，获取角色列表
     *
     * @param roleName 角色名称
     * @return 角色列表
     */
    List<SysRole> selectRoleList(String roleName);

    /**
     * 根据角色id，获取菜单
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    List<SysMenu> findMenuListByRoleId(Integer roleId);

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> findRolesByUserId(Integer userId);
}
