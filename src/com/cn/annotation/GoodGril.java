package com.cn.annotation;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/18 : 18:32.
 * @version : 1.0
 */
@ButefulGril(name = "guoxiaoyan",age = 23,love = true)
public class GoodGril {

    public static void main(String[] args) {
        ButefulGril annotation = GoodGril.class.getAnnotation(ButefulGril.class);
        int age = annotation.age();
        String name = annotation.name();
        boolean love = annotation.love();
        System.out.println("girl name is "+name+"\n"+"age is "+age+"\n she is my love "+love);

    }
}
