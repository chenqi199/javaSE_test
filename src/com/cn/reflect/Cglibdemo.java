package com.cn.reflect;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by chenqi on 2017/5/14 0014.
 */
public class Cglibdemo {
    public static void main(String[] args) {
        Enhancer enhancer=new Enhancer();

        ConcreteClassNoInterface c = new ConcreteClassNoInterface();
        enhancer.setSuperclass(ConcreteClassNoInterface.class);

                enhancer.setCallback(new ConcreteClassInterceptor());

        ConcreteClassNoInterface ccni=(ConcreteClassNoInterface)enhancer.create();
        ccni.getConcreteMethodA("shensy");

        ccni.getConcreteMethodB(0);
        ccni.getConcreteMethodB(20);
    }

}
class ConcreteClassNoInterface {

    public String getConcreteMethodA(String str){

        System.out.println("ConcreteMethod A ... "+str);

        return str;

    }

    public int getConcreteMethodB(int n){

        System.out.println("ConcreteMethod B ... "+n);

        return n+10;

    }

}
 class ConcreteClassInterceptor implements MethodInterceptor {

    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {

        System.out.println("Before:"+method);

        Object object=proxy.invokeSuper(obj, arg);

        System.out.println("After:"+method);

        return object;

    }

}