package com.mybatisplus;

import com.mybatisplus.Service.ProductService;
import com.mybatisplus.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author chenbiao
 * @Data 2022/6/19 6:22 PM
 */
@SpringBootTest
public class DataTest {

    @Resource
    private UserService userService;

    @Resource
    private ProductService productService;

    @Test
    void test1(){
        System.out.println(userService.getById(3));
        System.out.println(productService.getById(1));
    }
}
