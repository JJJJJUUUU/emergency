package com.yjzh.emergency.Lombda;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/18 16:44
 **/
public class Lombda2 {
    @Getter
    @Setter
    @ToString
    static
    class Fruit {
        private String name;
        private Double price;

        public Fruit(String name, Double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Fruit fruit = (Fruit) o;
            return java.util.Objects.equals(name, fruit.name) &&
                    java.util.Objects.equals(price, fruit.price);
        }

        @Override
        public int hashCode() {
            return java.util.Objects.hash(name, price);
        }
        // 注意equals和hashCode必须成对出现
    }


    public static void main(String[] args) {

        List<Fruit> fruitList = new ArrayList();
        fruitList.add(new Fruit("apple", (double) 6));
        fruitList.add(new Fruit("apple", (double) 6));
        fruitList.add(new Fruit("banana", (double) 7));
        fruitList.add(new Fruit("banana", (double) 7));
        fruitList.add(new Fruit("banana", (double) 7));
        fruitList.add(new Fruit("grape", (double) 8));

        Map<String,Long> map = fruitList.stream().collect(Collectors.groupingBy(Fruit::getName,Collectors.counting()));

        System.err.println(map);

        Optional<Map<String, Long>> map1 = Optional.ofNullable(null);

        System.err.println(map1);
    }

}
