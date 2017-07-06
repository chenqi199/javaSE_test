package com.cn.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chenqi on 2017/5/14 0014.
 * 通过反射执行方法
 */
public class TestRef {
    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Foo foo = new Foo("这个一个Foo对象！");
        Class clazz = foo.getClass();
        Method m1 = clazz.getDeclaredMethod("outInfo");
        Method m2 = clazz.getDeclaredMethod("setMsg", String.class);
        Method m3 = clazz.getDeclaredMethod("getMsg");
         m1.invoke(foo);
         String s = (String) m3.invoke(foo);
        System.out.println("============"+s);
        m2.invoke(foo, "重新设置msg信息！");
        String msg = (String) m3.invoke(foo);
        System.out.println(msg);
    }
}
class Foo {
    private String msg;

    public Foo(String msg) {
        this.msg = msg;
        System.out.println(this +"is name "+ msg);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void outInfo() {
        System.out.println("这是测试Java反射的测试类");
    }
}