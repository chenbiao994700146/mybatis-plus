package com.mybatisplus;

import com.mybatisplus.entity.Product;
import com.mybatisplus.mapper.ProductMapper;
import com.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author chenbiao
 * @Data 2022/6/19 5:28 PM
 */
@SpringBootTest
public class LockTest {

    @Resource
    private ProductMapper productMapper;
    @Test
    void test1(){
        //小李查询价格
        Product product = productMapper.selectById(1);
        System.out.println("小李查询的价格"+product.getPrice());
        //小王查询价格
        Product product1 = productMapper.selectById(1);
        System.out.println("小王查询的价格"+product1.getPrice());
        //小李将商品价格+50
        product.setPrice(product.getPrice()+50);
        productMapper.updateById(product);
        //小王将商品价格-30
        product1.setPrice(product1.getPrice()-30);
        int i = productMapper.updateById(product1);
        if(i==0){
            //操作失败，重试
            Product product3 = productMapper.selectById(1);
            product3.setPrice(product3.getPrice()-30);
            productMapper.updateById(product3);
        }

        //老板查询
        Product product2 = productMapper.selectById(1);
        System.out.println("老板的价格"+product2.getPrice());
    }
}
