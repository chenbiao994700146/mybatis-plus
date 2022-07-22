package com.mybatisplus;

import com.mybatisplus.Service.UserService;
import com.mybatisplus.config.MyAnnotation;
import com.mybatisplus.entity.User;
import com.mybatisplus.enums.StatusEnum;
import com.mybatisplus.mapper.UserMapper;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author chenbiao
 * @Data 2022/6/20 11:24 AM
 */
@SpringBootTest
public class InterceptorTest {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;


    @Test
    void test1(){
        System.out.println(userService.getById(1));
    }

    @Test
    void test2(){
        System.out.println(userMapper.selectMapById(1L));
    }

    @Test
    void test3(){
      Class us=UserMapper.class;
        Annotation[] declaredAnnotations = us.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            System.out.println(declaredAnnotation);
        }



        Field[] fields = us.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("新值"+field.getDeclaredAnnotation(MyAnnotation.class));
        }

        Method[] methods = us.getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            String name = method.getName();
            System.out.println(name);
            for (Annotation annotation : annotations) {

                System.out.println("全类名"+annotation.annotationType().getClass());
                System.out.println(annotation.annotationType().getTypeName());
                System.out.println(annotation.annotationType().getCanonicalName());
                System.out.println(annotation.annotationType().getSimpleName());
                System.out.println(annotation.annotationType().getComponentType());
                System.out.println(annotation.annotationType().getSigners());
                System.out.println("注解1："+annotation.annotationType().getAnnotation(MyAnnotation.class));
                System.out.println("注解2："+annotation.annotationType().getAnnotationsByType(MyAnnotation.class));
                System.out.println("注解3："+annotation.annotationType().getDeclaredAnnotation(MyAnnotation.class));
                System.out.println("注解4："+annotation.annotationType().getDeclaredAnnotationsByType(MyAnnotation.class));
                System.out.println("注解："+annotation.annotationType().getAnnotation(MyAnnotation.class).remark());
                System.out.println("注解："+annotation.annotationType().getAnnotation(MyAnnotation.class).value());
            }
        }
    }

    @Test
    void test4(){
        String s1="com.mybatisplus.mapper.UserMapper.selectMapById";
        String substring = s1.substring(0, s1.lastIndexOf("."));
        String substring1 = s1.substring(s1.lastIndexOf(".") + 1);
        System.out.println(substring);
        System.out.println(substring1);
        System.out.println(StatusEnum.SUCCESS.getAce());
    }
}
