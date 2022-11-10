package com.ycw.auth.ops.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * @Classname MenuMetaVo
 * @Description
 * </p>
 */
@Data
@AllArgsConstructor
public class MenuMetaVo implements Serializable {
    private String title;
    private String icon;
}
