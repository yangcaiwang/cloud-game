package com.ycw.auth.ops.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ycw.auth.ops.system.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * @Classname
 * @Description 角色菜单关系表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 通过userId，获取菜单id
     */
    @Select("SELECT rm.menu_id FROM sys_role_menu rm,sys_user_role ur,sys_user u WHERE u.user_id = #{userId} AND u.user_id = ur.user_id AND rm.role_id = ur.role_id")
    List<Integer> getMenuIdByUserId(Integer userId);

}
