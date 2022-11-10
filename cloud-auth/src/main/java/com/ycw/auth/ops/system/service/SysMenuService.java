package com.ycw.auth.ops.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.auth.ops.system.domain.SysMenu;
import com.ycw.auth.ops.system.dto.MenuDto;
import com.ycw.common.response.R;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 更新菜单信息
     *
     * @param menuDto 菜单Dto
     * @return 是否更新成功
     */
    boolean updateMenuById(MenuDto menuDto);

    /**
     * 删除菜单信息
     *
     * @param id 主键id
     * @return 通用返回体
     */
    R removeMenuById(Serializable id);

    /**
     * 根据用户id查找菜单树
     *
     * @param uid 用户id
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTree(Integer uid);

    /**
     * 根据父id查询菜单
     *
     * @param parentId 父id
     * @return 菜单信息
     */
    SysMenu getMenuById(Integer parentId);

    /**
     * 根据用户id查询权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    List<String> findPermsByUserId(Integer userId);
}
