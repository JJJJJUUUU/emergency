package com.yjzh.emergency.Lombda;

import java.io.Console;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/18 15:25
 **/


public class Lombda1 {


    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4);

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.err.println(integer);
            }
        };
        list.forEach(consumer);


        list.forEach(System.err::println);

        list.forEach(integer -> System.err.println(integer));

        //函数的几个要求：修饰符、返回值、函数名、参数、方法体
        //调用函数需要明确方法名和参数
        //使用lambda可以省略方法名

        //()->{}
        Runnable runnable = () -> System.err.println("hello");

        //创建stream 转换 stream 聚合
        //distinct filter map flatMap peek limit skip

        //collect reduce count sum allMatch
        as a = (s) -> System.err.println(s);



    }

}

@FunctionalInterface
interface as {
    void doSome(String s);
}
