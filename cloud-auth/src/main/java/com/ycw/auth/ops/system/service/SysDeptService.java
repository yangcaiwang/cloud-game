package com.ycw.auth.ops.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.auth.ops.system.domain.SysDept;
import com.ycw.auth.ops.system.dto.DeptDto;
import com.ycw.auth.ops.system.vo.DeptTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 查询部门信息
     *
     * @return 部门列表
     */
    List<SysDept> selectDeptList();

    /**
     * 更新部门
     *
     * @param deptDto 部门Dto
     * @return 是否更新成功
     */
    boolean updateDeptById(DeptDto deptDto);

    /**
     * 删除部门
     *
     * @param id 主键id
     * @return 是否删除成功
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据部门id查询部门名称
     *
     * @param deptId 部门id
     * @return 部门名称
     */
    String selectDeptNameByDeptId(Integer deptId);

    /**
     * 根据部门名称查询
     *
     * @param deptName 部门名字
     * @return 部门列表
     */
    List<SysDept> selectDeptListByDeptName(String deptName);

    /**
     * 通过此部门id查询于此相关的部门ids
     *
     * @param deptId 部门id
     * @return 部门列表
     */
    List<Integer> selectDeptIds(Integer deptId);

    /**
     * 获取部门树
     *
     * @return 部门树列表
     */
    List<DeptTreeVo> getDeptTree();
}
