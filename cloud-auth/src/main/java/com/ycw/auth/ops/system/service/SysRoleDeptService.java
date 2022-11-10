package com.ycw.auth.ops.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.auth.ops.system.domain.SysRoleDept;

import java.util.List;

/**
 * <p>
 * 角色与部门对应关系 服务类
 * </p>
 */
public interface SysRoleDeptService extends IService<SysRoleDept> {

    /**
     * 根据角色id查询部门ids
     *
     * @param roleId 角色id
     * @return 角色部门关系列表
     */
    List<SysRoleDept> getRoleDeptIds(Integer roleId);
}
