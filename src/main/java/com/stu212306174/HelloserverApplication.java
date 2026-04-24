package com.stu212306174;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stu212306174.mapper") // 这一行必须加
public class HelloserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloserverApplication.class, args);
    }
}