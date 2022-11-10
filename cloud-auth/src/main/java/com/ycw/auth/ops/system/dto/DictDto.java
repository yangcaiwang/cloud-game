package com.ycw.auth.ops.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * @Classname DictDto
 * @Description 字典dto
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictDto {

    private Integer id;

    private String dictName;

    private String dictCode;

    private String description;

    private Integer sort;

    private String remark;
}
