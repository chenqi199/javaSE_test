package com.cn.thinking_in_java;

import java.util.ArrayList;

/**
 * Created by 陈奇 on 2017/4/22 0022.
 */
public class InitAndLizition {
    public static void testCanChangeParames(String ... args) {
        for (String s:args) {
            System.out.print(s+" | ");
        }
//        System.out.println(args[5]);
    }

    public static void testObjectChangeParames(Object ... args) {
        for (Object o : args) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
//        testCanChangeParames("hs","vv","sjsj");
//        testCanChangeParames(new Integer[]{1,2});
        testObjectChangeParames("aa",2,3.4,1.11d,new Integer[]{1,2});
        testObjectChangeParames();
        ArrayList arrayList = new ArrayList();
    }
}
