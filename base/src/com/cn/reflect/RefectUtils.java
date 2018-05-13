package com.cn.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2018/3/8 : 18:03.
 * @version : 1.0
 */
public class RefectUtils {

    /**
     * 获取类的泛型的class
     *
     * @param c
     * @param index
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Class getGenericType(Class c, int index) {
        java.lang.reflect.Type genType = c.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        java.lang.reflect.Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("Index outof bounds");
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 获得泛型类的class
     * @param field 由于泛型在运行时会自动替换为E(如List<Element>会替换成List<E>)，所以在获取泛型时需要使用到声明时用的Field
     */
    private static Class<?> getGenerics(Field field) {
        // 关键的地方，如果是List类型，得到其Generic的类型
        Type genericType = field.getGenericType();
        Class<?> genericCls = null;
        if (genericType != null) {
            // 判断是否泛型类型
            if (genericType instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType)genericType;
                genericCls = (Class<?>) pt.getActualTypeArguments()[0];
            }
        }
        return genericCls;
    }

}
