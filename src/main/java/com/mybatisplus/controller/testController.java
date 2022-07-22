package com.mybatisplus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenbiao
 * @Data 2022/6/21 10:24 AM
 */
@RestController
public class testController {

    @GetMapping("/test")
    public void test1(){
        System.out.println("测试拦截器");
    }

    @GetMapping("/hello")
    public String test2(){
        return "hello";
    }
}
