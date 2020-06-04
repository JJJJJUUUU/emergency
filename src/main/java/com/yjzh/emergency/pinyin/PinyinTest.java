package com.yjzh.emergency.pinyin;

import com.github.promeg.pinyinhelper.Pinyin;
import org.hibernate.hql.internal.ast.tree.DisplayableNode;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/24 21:40
 **/
public class PinyinTest {

    /*public static void main(String[] args)  throws BadHanyuPinyinOutputFormatCombination {
        String name = "x互xx";
        char[] charArray = name.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = new String[0];

                    hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i],defaultFormat);

                String string =hanyuPinyinStringArray[0];
                pinyin.append(string);
            } else {
                pinyin.append(charArray[i]);
            }
        }
        System.err.println(pinyin);

        pinyin = new StringBuilder();
        // 设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        // 设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                if (hanyuPinyinStringArray != null) {
                    pinyin.append(hanyuPinyinStringArray[0].charAt(0));
                }
            }
        }
        System.err.println(pinyin);

        String[] pinyin2 = PinyinHelper.toHanyuPinyinStringArray('张');
        for (int i = 0; i < pinyin2.length; i++) {
            System.err.println(pinyin2[i]);
        }
    }*/

    public static void main(String[] args) {

        System.err.println(13&17);

        String input = "睾丸佘jzhangzzhs3h";

        String output = "睾丸佘张举zhang张zhs3好";

        //1、先判断是否是汉字匹配到的
        if (output.contains(input)) {
            System.err.println("高亮字符为：" + input);
        }
        //2、先获取输出结果的首字母和汉语拼音
        String outputAcronym = getAcronym(output);
        String outputPinyin = getFullPinyin(output);

        System.err.println(outputAcronym);
        System.err.println(outputPinyin);

        //2.1 建一个Map 把对应关系放进去
        Map<String, String> aMap = new LinkedHashMap<>();
        for (int i = 0; i < output.length(); i++) {
            //防止key重复
            aMap.put(outputAcronym.charAt(i) + "" + i, output.charAt(i) + "");
        }
        Map<String, String> bMap = new LinkedHashMap<>();
        for (int i = 0; i < output.length(); i++) {
            bMap.put(getFullPinyin(output.charAt(i) + "" + i), output.charAt(i) + "");
        }

        System.err.println(aMap);
        //3、判断是首字母匹配到的还是全拼匹配到的
        if (outputAcronym.contains(input)) {
            //获取该位置所对应的汉字
            String aMatch = "";
            int index = outputAcronym.indexOf(input);
            System.err.println(input);
            System.err.println(index);
            System.err.println(input.charAt(0));
            for (int i = 0; i < input.length(); i++) {
                aMatch += aMap.get(input.charAt(i) + "" + index);
                index++;
            }

            System.err.println("高亮字符为：" + aMatch);
        } else if (outputPinyin.contains(input)) {
            String bMatch = "";
            int index = 0;
            for (String key : bMap.keySet()) {
                System.err.println("input: " + input);
                //System.err.println("key: " + key);

                String keyString = key.substring(0, key.length() - (index + "").length());
                System.err.println("keyString: " + keyString);

                if (input.indexOf(keyString) == 0 || keyString.indexOf(input) == 0 && !StringUtils.isEmpty(input)) {
                    bMatch += bMap.get(key);
                    try {
                        input = input.substring(keyString.length());
                    } catch (Exception e) {
                        System.err.println("到头拉");
                    }
                }
                index++;
            }
            System.err.println("高亮字符为：" + bMatch);
        }


    }

    //完成一个


    public static String getFullPinyin(String str) {
        return Pinyin.toPinyin(str, "").toLowerCase();
    }

    public static String getAcronym(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            char[] pinyinArray = Pinyin.toPinyin(word).toCharArray();
            //String[] pinyinArray = chars;
            if (pinyinArray != null) {
                sb.append(pinyinArray[0]);
            } else {
                sb.append(word);
            }
        }
        return sb.toString().toLowerCase();
    }


}
