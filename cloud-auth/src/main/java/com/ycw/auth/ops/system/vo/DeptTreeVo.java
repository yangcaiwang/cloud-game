package com.ycw.auth.ops.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * @Classname DeptTreeVo
 * @Description 构建部门树vo
 * </p>
 */
@Setter
@Getter
@ToString
public class DeptTreeVo {

    private int id;
    private String label;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptTreeVo> children;
}
