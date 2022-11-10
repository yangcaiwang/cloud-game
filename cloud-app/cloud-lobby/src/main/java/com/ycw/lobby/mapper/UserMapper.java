package com.ycw.lobby.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ycw.lobby.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {}
