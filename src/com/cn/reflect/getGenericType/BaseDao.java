package com.cn.reflect.getGenericType;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/16 : 18:09.
 * @version : 1.0
 */
public interface BaseDao <T>{

   T load(T t);
   T intsert(T t);
   T delete(T t);
   T update(T t);

}
