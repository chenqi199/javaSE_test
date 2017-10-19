package com.cn.annotation;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/18 : 18:32.
 * @version : 1.0
 */
@BeautifulGirl(name = "xiaomi",age = 23,love = true)
public class GoodGirl {
    public static void main(String[] args) {
        BeautifulGirl annotation = GoodGirl.class.getAnnotation(BeautifulGirl.class);
        int age = annotation.age();
        String name = annotation.name();
        boolean love = annotation.love();
        System.out.println("girl name is "+name+"\n"+" age is "+age+"\n she is my love "+love);
    }
}
