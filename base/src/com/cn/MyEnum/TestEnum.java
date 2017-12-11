package com.cn.MyEnum;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/25 : 9:44.
 * @version : 1.0
 */
public enum TestEnum {


    getTestA(0,"A"),

    getTestB(2,"B"),
    getTestC(3,"C"),
    getTestD(4,"D"),
    getTestE(5,"E"),
    getTestF(6,"F"),
    getTestG(7,"G");

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private final int id;

    private final String name;


    TestEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }




}
