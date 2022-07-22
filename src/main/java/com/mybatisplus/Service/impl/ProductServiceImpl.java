package com.mybatisplus.Service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.Service.ProductService;
import com.mybatisplus.entity.Product;
import com.mybatisplus.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author chenbiao
 * @Data 2022/6/19 6:21 PM
 */
@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Value("${mySlaveDataSoure.name}")
    private String slaveName;
}
