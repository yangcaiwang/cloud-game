package com.ycw.common.appendix;

import com.ycw.common.propitem.PropItemStoreStruct;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * @Classname
 * @Description
 * </p>
 */
@Data
public class SystemPropItem extends Appendix{
    private List<PropItemStoreStruct> propItemStoreStructList;
}