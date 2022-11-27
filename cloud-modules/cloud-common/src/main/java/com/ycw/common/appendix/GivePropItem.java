package com.ycw.common.appendix;

import com.ycw.common.propitem.PropItemStoreStruct;
import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * @Classname
 * @Description </p>
 */
@Data
public class GivePropItem extends Appendix {
    private List<PropItemStoreStruct> propItemStoreStructList;
    // 赠送者用户id
    private Long giverUserId;
}
