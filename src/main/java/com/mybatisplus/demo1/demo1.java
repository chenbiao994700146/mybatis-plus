package com.mybatisplus.demo1;

/**
 * @author chenbiao
 * @Data 2022/7/23 4:27 PM
 */
public class demo1 {

    public static void main(String[] args) {

        System.out.println(test1());
    }

    public static String test1(){
        String s="hello";
        try {
            return s;
        }finally {
            s="cb";
        }
    }


    public void test5(){

    }


    public void  test2(){
        int s=1;
    }

    public void test3(){

    }

    public void test4(){

    }
}
