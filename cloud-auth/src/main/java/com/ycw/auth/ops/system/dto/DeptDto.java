package com.ycw.auth.ops.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * @Classname DeptDto
 * @Description 部门Dto
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptDto {

    private static final long serialVersionUID = 1L;

    private Integer deptId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;
}
