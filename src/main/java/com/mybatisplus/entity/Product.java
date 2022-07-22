package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.mybatisplus.enums.SexEnum;
import lombok.Data;

/**
 * @author chenbiao
 * @Data 2022/6/19 5:27 PM
 */

@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;

    @Version //标识乐观锁
    private Integer version;

   // private SexEnum sex;
}
