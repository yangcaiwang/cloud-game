package com.ycw.auth.ops.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ycw.auth.ops.system.domain.SysUser;
import com.ycw.auth.ops.system.dto.UserDto;
import com.ycw.auth.ops.system.service.SysSendMailService;
import com.ycw.auth.ops.system.service.SysUserService;
import com.ycw.auth.ops.system.util.PreUtil;
import com.ycw.common.constant.Constants;
import com.ycw.common.exception.ServiceException;
import com.ycw.common.response.R;
import com.ycw.auth.util.AuthUtil;
import com.ycw.operatelog.annotation.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.ycw.common.exception.enums.GlobalErrorCodeConstants.*;

/**
 * <p>
 * @Classname SysUserController
 * @Description 用户管理 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysSendMailService sysSendMailService;

    /**
     * 保存用户包括角色和部门
     */
    @OperateLog(description = "保存用户包括角色和部门")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public R insert(@RequestBody UserDto userDto) {
        return R.ok(userService.insertUser(userDto));
    }

    /**
     * 获取用户列表集合
     */
    @OperateLog(description = "查询用户集合")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:user:view')")
    public R getList(Page page, UserDto userDTO) {
        return R.ok(userService.getUsersWithRolePage(page, userDTO));
    }

    /**
     * 更新用户包括角色和部门
     */
    @OperateLog(description = "更新用户包括角色和部门")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:update')")
    public R update(@RequestBody UserDto userDto) {
        return R.ok(userService.updateUser(userDto));
    }

    /**
     * 删除用户包括角色和部门
     */
    @OperateLog(description = "根据用户id删除用户包括角色和部门")
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public R delete(@PathVariable("userId") Integer userId) {
        return R.ok(userService.removeUser(userId));
    }

    /**
     * 重置密码
     */
    @OperateLog(description = "重置密码")
    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:user:rest')")
    public R restPass(@PathVariable("userId") Integer userId) {
        return R.ok(userService.restPass(userId));
    }

    /**
     * 获取个人信息
     */
    @OperateLog(description = "获取个人信息")
    @GetMapping("/info")
    public R getUserInfo() {
        return R.ok(userService.findByUserInfoName(AuthUtil.getUser().getUsername()));
    }

    /**
     * 修改密码
     */
    @OperateLog(description = "修改密码")
    @PutMapping("/updatePass")
    @PreAuthorize("hasAuthority('sys:user:updatePass')")
    public R updatePass(@RequestParam String oldPass, @RequestParam String newPass) {
        // 校验密码流程
        SysUser sysUser = userService.findSecurityUserByUser(new SysUser().setUsername(AuthUtil.getUser().getUsername()));
        if (!PreUtil.validatePass(oldPass, sysUser.getPassword())) {
            throw new ServiceException(VALIDATE_ORIGINAL_PASSWORD_ERROR);
        }
        if (StrUtil.equals(oldPass, newPass)) {
            throw new ServiceException(VALIDATE_ORIGINAL_NEW_PASSWORD_NOT);
        }
        // 修改密码流程
        SysUser user = new SysUser();
        user.setUserId(sysUser.getUserId());
        user.setPassword(PreUtil.encode(newPass));
        return R.ok(userService.updateUserInfo(user));
    }

    /**
     * 检测用户名是否存在 避免重复
     */
    @PostMapping("/vailUserName")
    public R vailUserName(@RequestParam String userName) {
        SysUser sysUser = userService.findSecurityUserByUser(new SysUser().setUsername(userName));
        return R.ok(ObjectUtil.isNull(sysUser));
    }

    /**
     * 发送邮箱验证码
     */
    @PostMapping("/sendMailCode")
    public R sendMailCode(@RequestParam String to, HttpServletRequest request) {
        sysSendMailService.sendSimpleMail(to, request);
        return R.ok();
    }

    /**
     * 修改密码
     */
    @OperateLog(description = "修改邮箱")
    @PutMapping("updateEmail")
    @PreAuthorize("hasAuthority('sys:user:updateEmail')")
    public R updateEmail(@RequestParam String mail, @RequestParam String code, @RequestParam String pass, HttpServletRequest request) {
        // 校验验证码流程
        String ccode = (String) request.getSession().getAttribute(Constants.RESET_MAIL);
        if (ObjectUtil.isNull(ccode)) {
            throw new ServiceException(VALIDATE_CODE_NOT);
        }
        if (!StrUtil.equals(code.toLowerCase(), ccode)) {
            throw new ServiceException(VALIDATE_CODE_ERROR);
        }
        // 校验密码流程
        SysUser sysUser = userService.findSecurityUserByUser(new SysUser().setUsername(AuthUtil.getUser().getUsername()));
        if (!PreUtil.validatePass(pass, sysUser.getPassword())) {
            throw new ServiceException(VALIDATE_PASSWORD_ERROR);
        }
        // 修改邮箱流程
        SysUser user = new SysUser();
        user.setUserId(sysUser.getUserId());
        user.setEmail(mail);
        return R.ok(userService.updateUserInfo(user));
    }
}

