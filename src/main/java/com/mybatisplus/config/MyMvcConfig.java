package com.mybatisplus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author chenbiao
 * @Data 2022/6/21 10:28 AM
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Resource
    private MyHandlerInterceptor interceptor;


    /**
     * 添加自定义拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("拦截所有");
        registry.addInterceptor(interceptor).addPathPatterns("/*");
    }



}
