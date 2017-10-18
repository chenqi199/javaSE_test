package com.cn.reflect.TypePackage;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * 描述：
 * Created by  chen_q_i@163.com on 2017/10/12 : 18:12.
 *
 * @version : 1.0
 */
public class JavaBean {

    public String name;
    private int age;



    public void setAAAAge(int age) {
        this.age = age;
    }

    public void setNameTest(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            PropertyDescriptor[] pds = Introspector.getBeanInfo(JavaBean.class).getPropertyDescriptors();
            for(PropertyDescriptor pd : pds){
                System.out.println(pd.getName());
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

}
