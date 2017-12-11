package com.cn.reflect.getGenericType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * 描述：反射获取泛型类型
 *
 * @author chen_q_i@163.com
 * 2017/10/17 : 11:55.
 * @version : 1.0
 */

class A<T,S> {

}

interface C<K,V>{

}
class B extends A<String,String>implements C<String,Porsen> {

}

public class NewTest {
    public static void main(String[] args) {


//        super
        ParameterizedType parameterizedType = (ParameterizedType) B.class.getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Arrays.stream(actualTypeArguments).forEach(System.out::println);
//        interface
        ParameterizedType parameterizedType1 = (ParameterizedType) B.class.getGenericInterfaces()[0];
        Type[] actualTypeArguments1 = parameterizedType1.getActualTypeArguments();
        Arrays.stream(actualTypeArguments1).forEach(interfaceType -> System.out.println("interfaceReflectType : "+interfaceType));
    }

}
