package com.stu212306174.service;

import com.stu212306174.common.Result;
import com.stu212306174.dto.UserDTO;

public interface UserService {
    // 任务5已有的方法
    Result<String> register(UserDTO userDTO);
    Result<String> login(UserDTO userDTO);
    Result<String> getUserById(Long id);

    // 👇 任务6新增：分页查询用户方法
    Result<Object> getUserPage(Integer pageNum, Integer pageSize);
}