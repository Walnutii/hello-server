package com.stu212306174.controller;

import com.stu212306174.common.Result;
import com.stu212306174.dto.UserDTO;
import com.stu212306174.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 注册接口 ← 你现在缺的就是这个！
    @PostMapping
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    // 登录接口
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public Result<String> getById(@PathVariable Long id) {
        return Result.success("查询成功，用户ID：" + id);
    }
}