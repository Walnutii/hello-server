package com.stu212306174.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu212306174.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}