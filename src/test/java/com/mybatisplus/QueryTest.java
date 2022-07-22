package com.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mybatisplus.Service.UserService;
import com.mybatisplus.config.MyDataBatisConfig;
import com.mybatisplus.config.MyDataInterceptor;
import com.mybatisplus.entity.User;
import com.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author chenbiao
 * @Data 2022/6/19 4:16 PM
 */
@SpringBootTest
public class QueryTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MyDataInterceptor myDataInterceptor;

    @Test
    void  test1(){

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","AA").
                between("age",20,30).
                isNotNull("email");
        System.out.println(userMapper.selectList(queryWrapper));
    }

    @Test
    void test2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        System.out.println(userMapper.selectList(queryWrapper));
    }

    @Test
    void test3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        System.out.println(userMapper.delete(queryWrapper));
    }

    /**
     * UPDATE user SET name=? WHERE is_deleted=0 AND (age > ? AND name LIKE ? OR email IS NULL)
     */
    @Test
    void test4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20)
                .like("name","AA")
                .or()
                .isNull("email");
        User user=new User();
        user.setName("小明");
        System.out.println(userMapper.update(user, queryWrapper));
    }

    /**
     * UPDATE user SET name=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
     */
    @Test
    void test5(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","AA")
                .and(i->i.gt("age",20).or().isNull("email"));

        User user=new User();
        user.setName("小红");
        System.out.println(userMapper.update(user, queryWrapper));
    }

    @Test
    void test6(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age","email");
        System.out.println(userMapper.selectMaps(queryWrapper));
    }

    /**
     *  SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (id IN (select id from user ))
     */
    @Test
    void test7(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","select id from user ");
        System.out.println(userMapper.selectList(queryWrapper));
    }

    /**
     * UPDATE user SET name=? WHERE is_deleted=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
     */
    @Test
    void test8(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("name","AA")
                .and(i->i.gt("age",20).or().isNull("email"));
        userUpdateWrapper.set("name","小黑");
        System.out.println(userMapper.update(null, userUpdateWrapper));
    }

    /**
     *  SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (age >= ? AND age <= ?)
     */
    @Test
    void test9(){
        String name="";
        Integer ageBegin=20;
        Integer ageEnd=30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        if(ageBegin!=null){
            queryWrapper.ge("age",ageBegin);
        }
        if(ageEnd!=null){
            queryWrapper.le("age",ageEnd);
        }
        System.out.println(userMapper.selectList(queryWrapper));
    }

    @Test
    void test10(){
        String name="";
        Integer ageBegin=20;
        Integer ageEnd=30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name),"name",name)
                .ge(ageBegin!=null,"age",ageBegin)
                .le(ageEnd!=null,"age",ageEnd);
        System.out.println(userMapper.selectList(queryWrapper));

    }

    @Test
    void test11(){
        String name="";
        Integer ageBegin=20;
        Integer ageEnd=30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name),User::getName,name)
                .ge(ageBegin!=null,User::getAge,ageBegin)
                .le(ageEnd!=null,User::getAge,ageEnd);
        System.out.println(userMapper.selectList(queryWrapper));
    }

    @Test
    void test12(){
        LambdaUpdateWrapper<User> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.like(User::getName,"AA")
                .and(i->i.gt(User::getAge,20).or().isNull(User::getEmail));
        queryWrapper.set(User::getName,"小黑");
        System.out.println(userMapper.update(null, queryWrapper));
    }
}
