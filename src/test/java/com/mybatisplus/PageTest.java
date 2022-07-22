package com.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.entity.User;
import com.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author chenbiao
 * @Data 2022/6/19 5:07 PM
 */
@SpringBootTest
public class PageTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void test1(){
        Page<User> page = new Page<>(1,3);
        System.out.println(userMapper.selectPage(page, null));
    }

    @Test
    void test2(){
        Page<User> page = new Page<>(1,3);
        System.out.println(userMapper.selectPageByAge(page, 20));
    }
}
