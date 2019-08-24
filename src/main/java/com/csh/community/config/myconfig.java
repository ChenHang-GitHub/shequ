package com.csh.community.config;


import com.csh.community.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class myconfig implements WebMvcConfigurer {


    @Resource
    SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("拦截的配置文件");
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }
}
