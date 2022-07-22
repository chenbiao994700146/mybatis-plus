package com.mybatisplus;

import com.mybatisplus.entity.Product;
import com.mybatisplus.enums.SexEnum;
import com.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author chenbiao
 * @Data 2022/6/19 5:49 PM
 */
@SpringBootTest
public class EnumTest {

    @Resource
    private ProductMapper productMapper;

    @Test
    void test1(){
        Product pro=new Product();
        pro.setName("admin");
        pro.setPrice(40);
       // pro.setSex(SexEnum.MALE);
       // System.out.println(pro.getSex());
        productMapper.insert(pro);
    }
    public static void main(String[] args) {
        String s=null;
            if("s".equals(s)){
                System.out.println(true);
            }
    }
}
