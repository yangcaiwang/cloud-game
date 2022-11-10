package com.ycw.auth.ops.system.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.auth.ops.system.domain.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户id，查询用户角色关系
     *
     * @param userId 用户id
     * @return 用户角色关系列表
     */
    List<SysUserRole> selectUserRoleListByUserId(Integer userId);
}
