package com.ycw.auth.ops.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.auth.ops.system.domain.SysUser;
import com.ycw.auth.ops.system.dto.UserDto;

import java.util.Set;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 分页查询用户信息（含有角色信息）
     *
     * @param page    分页对象
     * @param userDTO 参数列表
     * @return 用户信息分页结果
     */
    IPage<SysUser> getUsersWithRolePage(Page page, UserDto userDTO);

    /**
     * 保存用户以及角色部门等信息
     *
     * @param userDto 用户Dto
     * @return 是否保存成功
     */
    boolean insertUser(UserDto userDto);

    /**
     * 更新用户以及角色部门等信息
     *
     * @param userDto 用户Dto
     * @return 是否更新成功
     */
    boolean updateUser(UserDto userDto);

    /**
     * 删除用户信息
     *
     * @param userId 用户id
     * @return 是否删除成功
     */
    boolean removeUser(Integer userId);

    /**
     * 重置密码
     *
     * @param userId 用户id
     * @return 是否重置成功
     */
    boolean restPass(Integer userId);

    /**
     * 通过用户名，查找用户个人信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUserInfoName(String username);

    /**
     * 根据用户id，查询权限
     *
     * @param userId 用户id
     * @return
     */
    Set<String> findPermsByUserId(Integer userId);

    /**
     * 通过用户id，查询角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    Set<String> findRoleIdByUserId(Integer userId);

    /**
     * 账户密码登录
     *
     * @param username 用户账号
     * @param password 用户密码
     * @return 登陆用户信息
     */
    String login(String username, String password);
    /**
     * 注册用户
     *
     * @param userDTO 用户Dto
     * @return 是否注册成功
     */
    boolean register(UserDto userDTO);

    /**
     * 修改用户信息
     *
     * @param sysUser 用户信息
     * @return 是否修改成功
     */
    boolean updateUserInfo(SysUser sysUser);

    /**
     * 通过用户信息，查找用户(id/用户名/手机号)
     *
     * @param sysUser 用户信息
     * @return 用户信息
     */
    SysUser findSecurityUserByUser(SysUser sysUser);
}
