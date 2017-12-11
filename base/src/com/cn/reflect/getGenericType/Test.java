package com.cn.reflect.getGenericType;

import java.lang.reflect.Type;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/17 : 11:48.
 * @version : 1.0
 */
public class Test {
    public static void main(String[] args) {
        Type genericSuperclass = PorsenDaoImp.class.getGenericSuperclass();

        System.out.println(genericSuperclass);
    }
}
