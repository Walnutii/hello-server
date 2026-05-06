package com.stu212306174.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 👇 把原来的 addInterceptors 全部删掉或注释
    // 任务8用SpringSecurity，不再用拦截器！

}