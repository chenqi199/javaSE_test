package com.cn.reflect.BeanInfoDemo;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/19 : 15:02.
 * @version : 1.0
 */
public class BuildViewFromEntity {

    public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException {

        Girl girl = new Girl();
        girl.setName("111111111111111111111");
        girl.setId(2323L);
        girl.setCtime(System.currentTimeMillis());
        girl.setUtime(System.currentTimeMillis() + 22323333);
        NewGirl newGirl = new NewGirl();
        voUtils(girl, newGirl);
        System.out.println(newGirl);
    }


    public static void voUtils(Object target, Object voObj) throws IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();
        PropertyDescriptor[] ps = Introspector.getBeanInfo(voObj.getClass()).getPropertyDescriptors();

        Arrays.stream(pds)
                .filter(pd -> !"class".equals(pd.getName()) && pd.getReadMethod() != null)
                .forEach(pd -> {
                    Object value = null;
                    try {
                        value = pd.getReadMethod().invoke(target);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    if (value != null) {
                        System.out.println(value);
                        Object finalValue = value;

                        Arrays.stream(ps)
                                .filter(p -> p.getName().equals(pd.getName()))
                                .forEach(p -> {
                                    try {
                                        p.getWriteMethod().invoke(voObj, finalValue);
                                    } catch (IllegalAccessException | InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                });
                    }

                });


    }

}
