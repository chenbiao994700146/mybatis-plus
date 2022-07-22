package com.mybatisplus.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenbiao
 * @Data 2022/6/21 11:04 AM
 */
//Configuration
public class MyFilterConfig {


    public FilterRegistrationBean<MyFilter> filterRegistrationBean(){

        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();

        return bean;
    }
}
