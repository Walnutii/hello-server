package com.stu212306174.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.stu212306174.common.Result;
import com.stu212306174.common.ResultCode;
import com.stu212306174.dto.UserDTO;
import com.stu212306174.entity.User;
import com.stu212306174.mapper.UserMapper;
import com.stu212306174.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<String> register(UserDTO userDTO) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUsername, userDTO.getUsername());
        if (userMapper.selectOne(query) != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userMapper.insert(user);
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUsername, userDTO.getUsername());
        User user = userMapper.selectOne(query);

        if (user == null) return Result.error(ResultCode.USER_NOT_EXIST);
        if (!user.getPassword().equals(userDTO.getPassword())) return Result.error(ResultCode.PASSWORD_ERROR);

        return Result.success(UUID.randomUUID().toString());
    }
}