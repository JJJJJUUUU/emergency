package com.yjzh.emergency.pinyin;

import java.io.Serializable;
import java.util.IdentityHashMap;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/25 9:43
 **/
public class CNPinyin <T extends CN> implements Serializable, Comparable<CNPinyin<T>> {


    public static void main(String[] args) {
        IdentityHashMap<String,String> map =new IdentityHashMap<>();

        map.put(new String("xx"),"234");
        map.put(new String("xx"),"123");
        map.put(new String("xx"),"aaa");
        map.put(new String("xx"),"ccc");
        System.err.println(map.containsKey("xx"));


    }

    /**
     * 对应首字首拼音字母
     */
    char firstChar;
    /**
     * 所有字符中的拼音首字母
     */
    String firstChars;
    /**
     * 对应的所有字母拼音
     */
    String[] pinyins;

    /**
     * 拼音总长度
     */
    int pinyinsTotalLength;

    public final T data;

    CNPinyin(T data) {
        this.data = data;
    }

    public char getFirstChar() {
        return firstChar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("--firstChar--").append(firstChar).append("--pinyins:");
        for (String str : pinyins) {
            sb.append(str);
        }
        return sb.toString();
    }

    int compareValue() {
        if (firstChar == 21) {
            return 'Z' + 1;
        }
        return firstChar;
    }

    @Override
    public int compareTo(CNPinyin<T> tcnPinyin) {
        int compare = compareValue() - tcnPinyin.compareValue();
        if (compare == 0) {
            String chinese1 = data.chinese();
            String chinese2 = tcnPinyin.data.chinese();
            return chinese1.compareTo(chinese2);
        }
        return compare;
    }
}