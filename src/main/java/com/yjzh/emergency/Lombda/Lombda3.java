package com.yjzh.emergency.Lombda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/31 17:29
 **/
public class Lombda3 {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("1");
        list.add("a");
        list.add("b");

        list.forEach(a->{
            System.err.println(a);
        });

    }
}
