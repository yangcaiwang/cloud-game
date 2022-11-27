package com.ycw.common.appendix;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ycw.common.propitem.PropItemStoreStruct;

/**
 * <p>
 * @Classname Appendix
 * @Description
 * </p>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS) // 抽象类生成实例对象
public abstract class Appendix {
}
