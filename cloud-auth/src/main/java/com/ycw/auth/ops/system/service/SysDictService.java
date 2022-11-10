package com.ycw.auth.ops.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycw.auth.ops.system.domain.SysDict;
import com.ycw.auth.ops.system.domain.SysDictItem;
import com.ycw.auth.ops.system.dto.DictDto;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 更新字典
     *
     * @param dictDto 字典Dto
     * @return 是否更新成功
     */
    boolean updateDict(DictDto dictDto);

    /**
     * 根据主键Id删除字典
     *
     * @param id 主键id
     * @return 是否删除成功
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据字典名称查询字段详情
     *
     * @param dictName 字典名称
     * @return 字典详情列表
     */
    List<SysDictItem> queryDictItemByDictName(String dictName);
}
