package com.mybatisplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenbiao
 * @Data 2022/6/20 10:52 AM
 */
//@Configuration
public class MyDataBatisConfig {

   // @Bean
    public MyDataInterceptor myDataInterceptor(){
        return new MyDataInterceptor();
    }
}
