package com.ycw.auth.ops.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.auth.ops.system.service.SysDeptService;
import com.ycw.auth.ops.system.service.SysMenuService;
import com.ycw.auth.ops.system.service.SysUserRoleService;
import com.ycw.auth.ops.system.service.SysUserService;
import com.ycw.common.enums.UserType;
import com.ycw.common.utils.SpringBeanUtil;
import com.ycw.auth.util.AuthUserDetails;
import com.ycw.auth.ops.system.domain.SysUser;
import com.ycw.auth.ops.system.domain.SysUserRole;
import com.ycw.auth.ops.system.util.PreUtil;
import com.ycw.common.enums.DataSourcesType;
import com.ycw.common.exception.ServiceException;
import com.ycw.common.utils.JwtTokenUtil;
import com.ycw.datasource.annotation.DataSource;
import com.ycw.redis.service.RedisService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ycw.auth.ops.system.dto.UserDto;
import com.ycw.auth.ops.system.mapper.SysUserMapper;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.ycw.common.constant.Constants.AUTHORITIES;
import static com.ycw.common.exception.enums.GlobalErrorCodeConstants.VALIDATE_PHONE_ALREADY_REGISTER;
import static com.ycw.common.exception.enums.GlobalErrorCodeConstants.VALIDATE_USERNAME_ALREADY_REGISTER;

/**
 * <p>
 * ????????? ???????????????
 * </p>
 *
 * @author lihaodong
 * @since 2019-04-21
 */
@Service
@DependsOn("springBeanUtil")
@DataSource(name = DataSourcesType.SYS)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    RedisService redisService;
    private final AuthenticationManager authenticationManager = SpringBeanUtil.getBean(AuthenticationManager.class);

    @Override
    public IPage<SysUser> getUsersWithRolePage(Page page, UserDto userDTO) {

        if (ObjectUtils.anyNotNull(userDTO) && userDTO.getDeptId() != null) {
            userDTO.setDeptList(sysDeptService.selectDeptIds(userDTO.getDeptId()));
        }
        return baseMapper.getUserVosPage(page, userDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUser(UserDto userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        // ???????????? 123456
        sysUser.setPassword(PreUtil.encode("123456"));
        baseMapper.insertUser(sysUser);
        List<SysUserRole> userRoles = userDto.getRoleList().stream().map(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(item);
            sysUserRole.setUserId(sysUser.getUserId());
            return sysUserRole;
        }).collect(Collectors.toList());

        return sysUserRoleService.saveBatch(userRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(UserDto userDto) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDto, sysUser);
        baseMapper.updateById(sysUser);
        sysUserRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, sysUser.getUserId()));
        List<SysUserRole> userRoles = userDto.getRoleList().stream().map(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(item);
            sysUserRole.setUserId(sysUser.getUserId());
            return sysUserRole;
        }).collect(Collectors.toList());

        return sysUserRoleService.saveBatch(userRoles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeUser(Integer userId) {
        baseMapper.deleteById(userId);
        return sysUserRoleService.remove(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
    }

    @Override
    public boolean restPass(Integer userId) {
        return baseMapper.updateById(new SysUser().setPassword("123456").setUserId(userId)) > 0;
    }

    @Override
    public SysUser findByUserInfoName(String username) {
        SysUser sysUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPhone, SysUser::getEmail, SysUser::getPassword, SysUser::getDeptId, SysUser::getJobId, SysUser::getAvatar)
                .eq(SysUser::getUsername, username));
        // ????????????
        sysUser.setDeptName(sysDeptService.selectDeptNameByDeptId(sysUser.getDeptId()));
        // ????????????
//        sysUser.setJobName(jobService.selectJobNameByJobId(sysUser.getJobId()));
        return sysUser;
    }

    @Override
    public Set<String> findPermsByUserId(Integer userId) {
        return sysMenuService.findPermsByUserId(userId).stream().filter(StringUtils::isNotEmpty).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findRoleIdByUserId(Integer userId) {
        return sysUserRoleService
                .selectUserRoleListByUserId(userId)
                .stream()
                .map(sysUserRole -> "ROLE_" + sysUserRole.getRoleId())
                .collect(Collectors.toSet());
    }

    @Override
    public String login(String username, String password) {
        //????????????
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        //??????????????????
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUserDetails userDetail = (AuthUserDetails) authentication.getPrincipal();
        // ??????????????????redis
        redisService.setCacheObject(AUTHORITIES + userDetail.getUserId(), authentication, 1L, TimeUnit.DAYS);
        //??????token
        return JwtTokenUtil.generateToken(userDetail.getUserId().longValue(), userDetail.getUsername(), UserType.SYS_USER);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean register(UserDto userDTO) {
        // ???????????????????????????
        SysUser byUserInfoName = findSecurityUser(userDTO.getUsername());
        if (ObjectUtil.isNotNull(byUserInfoName)) {
            throw new ServiceException(VALIDATE_USERNAME_ALREADY_REGISTER);
        }
        SysUser securityUser = findSecurityUser(userDTO.getPhone());
        if (ObjectUtil.isNotNull(securityUser)) {
            throw new ServiceException(VALIDATE_PHONE_ALREADY_REGISTER);
        }
        userDTO.setDeptId(6);
        userDTO.setLockFlag("0");
        SysUser sysUser = new SysUser();
        // ????????????
        BeanUtil.copyProperties(userDTO, sysUser);
        // ??????????????????
        sysUser.setPassword(PreUtil.encode(userDTO.getPassword()));
        baseMapper.insertUser(sysUser);
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(14);
        sysUserRole.setUserId(sysUser.getUserId());
        return sysUserRoleService.save(sysUserRole);
    }

    @Override
    public boolean updateUserInfo(SysUser sysUser) {
        return baseMapper.updateById(sysUser) > 0;
    }

    @Override
    public SysUser findSecurityUserByUser(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword);
        if (StrUtil.isNotEmpty(sysUser.getUsername())) {
            select.eq(SysUser::getUsername, sysUser.getUsername());
        } else if (StrUtil.isNotEmpty(sysUser.getPhone())) {
            select.eq(SysUser::getPhone, sysUser.getPhone());
        } else if (ObjectUtil.isNotNull(sysUser.getUserId()) && sysUser.getUserId() != 0) {
            select.eq(SysUser::getUserId, sysUser.getUserId());
        }
        return baseMapper.selectOne(select);
    }

    private SysUser findSecurityUser(String userIdOrUserNameOrPhone) {
        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery()
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getPassword)
                .eq(SysUser::getUsername, userIdOrUserNameOrPhone)
                .or()
                .eq(SysUser::getPhone, userIdOrUserNameOrPhone)
                .or()
                .eq(SysUser::getUserId, userIdOrUserNameOrPhone);
        return baseMapper.selectOne(select);
    }
}
