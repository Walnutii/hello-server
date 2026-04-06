package com.stu212306174.service;

import com.stu212306174.common.Result;
import com.stu212306174.dto.UserDTO;

public interface UserService {
    Result<String> register(UserDTO userDTO);
    Result<String> login(UserDTO userDTO);
}