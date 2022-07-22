package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mybatisplus.config.MyAnnotation;
import lombok.Data;

/**
 * @author chenbiao
 * @Data 2022/6/18 7:01 PM
 */
@MyAnnotation
@Data
public class User {

    /**
     * 将属性
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableLogic
    private Integer isDeleted;

    @MyAnnotation
    public void show(){

    }
}
