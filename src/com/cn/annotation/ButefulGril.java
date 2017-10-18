package com.cn.annotation;

import javax.annotation.Resource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/10/18 : 18:00.
 * @version : 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ButefulGril {
    public enum HowButeful{}
    public String name() default "xiaoxiaomi";
    public int age() default 24;
    public  boolean love() default true;
}
