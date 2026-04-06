package com.stu212306174;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 核心注解：Spring Boot 启动入口
@SpringBootApplication
// 必须加！扫描你的 mapper 包，否则数据库操作会失效
@MapperScan("com.stu212306174.mapper")
public class HelloserverApplication {

    public static void main(String[] args) {
        // 启动 Spring Boot 项目
        SpringApplication.run(HelloserverApplication.class, args);
    }

}