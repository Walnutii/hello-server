package com.stu212306174.vo;

import lombok.Data;

@Data
public class UserDetailVO {
    // sys_user 表字段
    private Long userId;
    private String username;

    // user_info 表字段
    private String realName;
    private String phone;
    private String address;
}