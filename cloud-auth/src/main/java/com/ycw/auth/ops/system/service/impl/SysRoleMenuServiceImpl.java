package com.ycw.auth.ops.system.service.impl;
;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.auth.ops.system.domain.SysRoleMenu;
import com.ycw.auth.ops.system.mapper.SysRoleMenuMapper;
import com.ycw.auth.ops.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public List<Integer> getMenuIdByUserId(Integer userId) {
        return baseMapper.getMenuIdByUserId(userId);
    }
}
