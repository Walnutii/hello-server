package com.stu212306174.controller;

import com.stu212306174.common.Result;
import com.stu212306174.dto.UserDTO;
import com.stu212306174.service.UserService;
import com.stu212306174.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 任务5已有的注册接口
    @PostMapping
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    // 任务5已有的登录接口
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    // 任务5已有的根据ID查询接口
    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 任务6新增的分页接口
    @GetMapping("/page")
    public Result<Object> getUserPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        return userService.getUserPage(pageNum, pageSize);
    }

    // ====================== 任务7 只加这一个接口 ======================
    @GetMapping("/{id}/detail")
    public Result<UserDetailVO> getUserDetail(@PathVariable Long id) {
        return userService.getUserDetail(id);
    }

}