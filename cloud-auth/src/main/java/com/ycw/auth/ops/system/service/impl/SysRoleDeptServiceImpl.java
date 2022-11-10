package com.ycw.auth.ops.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.auth.ops.system.domain.SysRoleDept;
import com.ycw.auth.ops.system.mapper.SysRoleDeptMapper;
import com.ycw.auth.ops.system.service.SysRoleDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色与部门对应关系 服务实现类
 * </p>
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDept> implements SysRoleDeptService {
    @Override
    public List<SysRoleDept> getRoleDeptIds(Integer roleId) {
        return baseMapper.selectList(Wrappers.<SysRoleDept>lambdaQuery().select(SysRoleDept::getDeptId).eq(SysRoleDept::getRoleId,roleId));
    }
}
