package com.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.config.MyAnnotation;
import com.mybatisplus.entity.User;
import com.mybatisplus.enums.StatusEnum;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author chenbiao
 * @Data 2022/6/18 7:04 PM
 */

public interface UserMapper extends BaseMapper<User> {

    @MyAnnotation(value = {StatusEnum.SUCCESS})
    Map<String,?>selectMapById(@Param("id") Long id);

    Page<User> selectPageByAge(@Param("page") Page<User> page, @Param("age") Integer age);
}
