package com.cn.reflect.BeanInfoDemo;

import com.cn.annotation.PrimaryKey;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/19 : 9:29.
 * @version : 1.0
 */
public class GrilBeanInfo {

    public static void main(String[] args) throws IntrospectionException {
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(Girl.class).getPropertyDescriptors();
        Arrays.stream(propertyDescriptors).forEach(p ->{
            System.out.println (p.getName());
            String fieldName = p.getName();
            if(!"class".equals(fieldName)){
                try {
                    Field field = getFieldByName(fieldName, Girl.class);
                    if (field!=null){
                        PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                        if (primaryKey!=null){
                            String name = p.getName();
                            System.out.println("primaryKey:::::::"+name);
                            String dbName = name;
                            Method writeMethod = p.getWriteMethod();
                            Method readMethod = p.getReadMethod();
                            FieldInfo tempFieldInfo = new FieldInfo(field, name,
                                    dbName, writeMethod, readMethod);
                            System.out.println(tempFieldInfo);
//                            for (FieldInfo fieldInfo : fields) {
//                                if (fieldInfo.equals(tempFieldInfo)) {
//                                    this.primaryFieldInfo = fieldInfo;
//                                    break;
//                                }
//                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });





    }
    /**
     * 根据属性名字，获取类的属性
     *
     * @param fieldName
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Field getFieldByName(String fieldName, Class<?> clazz) throws Exception {
        Class<?> superClass = clazz;
        Field field = null;
        while (superClass != null && field == null) {
            try {
                field = superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
            }
            superClass = superClass.getSuperclass();
        }
        return field;
    }



}
