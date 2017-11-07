package com.cn.MyEnum;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/25 : 9:52.
 * @version : 1.0
 */
public class TestMain {


    public static void main(String[] args) {
        TestEnum getTestA = TestEnum.getTestC;
        System.out.println(getTestA.getName());
        System.out.println(getTestA.getId());
    }

}
