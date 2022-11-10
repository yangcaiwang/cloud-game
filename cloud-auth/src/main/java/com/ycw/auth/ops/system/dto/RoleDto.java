package com.ycw.auth.ops.system.dto;

import com.ycw.auth.ops.system.domain.SysRoleMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * @Classname RoleDto
 * @Description 角色Dto
 * </p>
 */
@Setter
@Getter
public class RoleDto {

    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private String delFlag;
    private int dsType;
    List<SysRoleMenu> roleMenus;
    List<Integer> roleDepts;



}
