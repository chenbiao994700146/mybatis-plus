package com.mybatisplus.Service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.Service.UserService;
import com.mybatisplus.entity.User;
import com.mybatisplus.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author chenbiao
 * @Data 2022/6/18 7:41 PM
 */
@DS("master")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
