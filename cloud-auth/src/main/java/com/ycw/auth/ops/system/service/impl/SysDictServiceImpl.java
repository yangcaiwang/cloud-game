package com.ycw.auth.ops.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycw.auth.ops.system.domain.SysDict;
import com.ycw.auth.ops.system.domain.SysDictItem;
import com.ycw.auth.ops.system.dto.DictDto;
import com.ycw.auth.ops.system.mapper.SysDictMapper;
import com.ycw.auth.ops.system.service.SysDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateDict(DictDto dictDto) {
        SysDict sysDict = new SysDict();
        BeanUtil.copyProperties(dictDto, sysDict);
        return updateById(sysDict);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public List<SysDictItem> queryDictItemByDictName(String dictName) {
        return baseMapper.queryDictItemByDictName(dictName);
    }
}
