package com.ycw.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据对象
 *
 * @author ai-cloud
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 列表数据
     */
    private List<?> list;
}