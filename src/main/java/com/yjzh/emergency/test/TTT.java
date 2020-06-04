package com.yjzh.emergency.test;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.util.StringUtils;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/6/3 10:52
 **/
public class TTT {

    public static void main(String[] args) {
        String accessNumber = "10657125360155";

        System.err.println(accessNumber.indexOf(9));

        if (StringUtils.isEmpty(accessNumber)) {

        } else if (!(accessNumber.length()>=10 && accessNumber.charAt(9) == '6')) {
            System.err.println(3434);
        }

    }
}
