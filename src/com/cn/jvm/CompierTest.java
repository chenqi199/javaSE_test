package com.cn.jvm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/14 : 10:26.
 * @version : 1.0
 */
public class CompierTest {
    public static void main(String[] args) {
//        int a =0;
//        boolean b = false;
//        int c=1;
//        int d=a+b;
        List<Integer> list = Arrays.asList(1,2,3,4);
        long count = list.stream().reduce(0,Integer::sum);
        System.out.println(count);
        IntStream.rangeClosed(1, 100).forEach(System.out::println );
    }
}
