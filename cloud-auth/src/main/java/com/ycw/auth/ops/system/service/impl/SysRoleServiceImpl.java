package com.ycw.auth.ops.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.auth.ops.system.domain.SysMenu;
import com.ycw.auth.ops.system.domain.SysRole;
import com.ycw.auth.ops.system.domain.SysRoleDept;
import com.ycw.auth.ops.system.domain.SysRoleMenu;
import com.ycw.auth.ops.system.dto.RoleDto;
import com.ycw.auth.ops.system.mapper.SysRoleMapper;
import com.ycw.auth.ops.system.service.SysRoleDeptService;
import com.ycw.auth.ops.system.service.SysRoleMenuService;
import com.ycw.auth.ops.system.service.SysRoleService;
import com.ycw.auth.ops.system.service.SysUserService;
import com.ycw.auth.util.AuthUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysRoleDeptService sysRoleDeptService;

    @Resource
    private SysUserService sysUserService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRoleMenu(RoleDto roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        Integer deptId = sysUserService.findByUserInfoName(AuthUtil.getUser().getUsername()).getDeptId();
        List<Integer> ids = Arrays.asList(deptId);
        baseMapper.insertRole(sysRole);
        Integer roleId = sysRole.getRoleId();
        //维护角色菜单
        List<SysRoleMenu> roleMenus = roleDto.getRoleMenus();
        if (CollectionUtil.isNotEmpty(roleMenus)) {
            List<SysRoleMenu> rms = roleMenus.stream().map(sysRoleMenu -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(sysRoleMenu.getMenuId());
                return roleMenu;
            }).collect(Collectors.toList());
            sysRoleMenuService.saveBatch(rms);
        }
        // 维护角色部门权限
        if (CollectionUtil.isNotEmpty(ids)) {
            List<SysRoleDept> roleDepts = ids.stream().map(integer -> {
                SysRoleDept sysRoleDept = new SysRoleDept();
                sysRoleDept.setDeptId(integer);
                sysRoleDept.setRoleId(roleId);
                return sysRoleDept;
            }).collect(Collectors.toList());

            sysRoleDeptService.saveBatch(roleDepts);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRoleMenu(RoleDto roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);

        List<SysRoleMenu> roleMenus = roleDto.getRoleMenus();
        sysRoleMenuService.remove(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, sysRole.getRoleId()));
        sysRoleDeptService.remove(Wrappers.<SysRoleDept>query().lambda().eq(SysRoleDept::getRoleId, sysRole.getRoleId()));

        if (CollectionUtil.isNotEmpty(roleMenus)) {
            sysRoleMenuService.saveBatch(roleMenus);
        }
        Integer deptId = sysUserService.findByUserInfoName(AuthUtil.getUser().getUsername()).getDeptId();
        List<Integer> ids = Arrays.asList(deptId);
        if (CollectionUtil.isNotEmpty(ids)) {
            List<SysRoleDept> roleDepts = ids.stream().map(integer -> {
                SysRoleDept sysRoleDept = new SysRoleDept();
                sysRoleDept.setDeptId(integer);
                sysRoleDept.setRoleId(roleDto.getRoleId());
                return sysRoleDept;
            }).collect(Collectors.toList());
            sysRoleDeptService.saveBatch(roleDepts);
        }
        baseMapper.updateById(sysRole);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        sysRoleMenuService.remove(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleId, id));
        sysRoleDeptService.remove(Wrappers.<SysRoleDept>query().lambda().eq(SysRoleDept::getRoleId, id));
        return super.removeById(id);
    }

    @Override
    public List<SysRole> selectRoleList(String roleName) {
        LambdaQueryWrapper<SysRole> sysRoleLambdaQueryWrapper = Wrappers.<SysRole>lambdaQuery();
        if (StrUtil.isNotEmpty(roleName)){
            sysRoleLambdaQueryWrapper.like(SysRole::getRoleName,roleName);
        }
        List<SysRole> sysRoles = baseMapper.selectList(sysRoleLambdaQueryWrapper);
        return sysRoles.stream().peek(sysRole ->
                sysRole.setRoleDepts(sysRoleDeptService.getRoleDeptIds(sysRole.getRoleId()).stream().map(SysRoleDept::getDeptId).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }

    @Override
    public List<SysMenu> findMenuListByRoleId(Integer roleId) {
        return baseMapper.findMenuListByRoleId(roleId);
    }

    @Override
    public List<SysRole> findRolesByUserId(Integer userId) {
        return baseMapper.listRolesByUserId(userId);
    }
}
