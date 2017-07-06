package com.cn.collectionDemo;

/**
 * Created by chenqi on 2017/7/4 0004 : 上午 10:30.
 *
 * @version : 1.0
 * @description :可在ArraySet 以及HashSet间作出选择，具体取决于 Set的大小（如果需要从一个 Set中获得一个顺序列
 * 表，请用TreeSet；注释⑧）。下面这个测试程序将有助于大家作出这方面的抉择
 * 可以看出来hashSet有很大的插入优势
 * 打印结果
 * Testing java.util.TreeSet size 10
add: 3.0
contains: 1.0
iteration: 2.0
Testing java.util.HashSet size 10
add: 0.0
contains: 1.0
iteration: 2.0
Testing java.util.TreeSet size 100
add: 6.0
contains: 3.0
iteration: 3.0
Testing java.util.HashSet size 100
add: 2.0
contains: 3.0
iteration: 7.0
Testing java.util.HashSet size 100000
add: 1491.0
contains: 1161.0
iteration: 2199.0
Testing java.util.TreeSet size 100000
add: 3822.0
contains: 3182.0
iteration: 2097.0

 */


import java.util.*;
public class SetPerformance {
    private static final int REPS = 200;
    private abstract static class Tester {
        String name;
        Tester(String name) { this.name = name; }
        abstract void test(Set s, int size);
    }
    private static Tester[] tests = {
            new Tester("add") {
                void test(Set s, int size) {
                    for(int i = 0; i < REPS; i++) {
                        s.clear();
                        Collection1.fill(s, size);
                    }
                }
            },
            new Tester("contains") {
                void test(Set s, int size) {
                    for(int i = 0; i < REPS; i++)
                        for(int j = 0; j < size; j++)
                            s.contains(Integer.toString(j));
                }
            },
            new Tester("iteration") {
                void test(Set s, int size) {
                    for(int i = 0; i < REPS * 10; i++) {
                        Iterator it = s.iterator();
                        while(it.hasNext())
                            it.next();
                    }
                }
            },
    };
    public static void test(Set s, int size) {
// A trick to print out the class name:
        System.out.println("Testing " +
                s.getClass().getName() + " size " + size);
        Collection1.fill(s, size);
        for(int i = 0; i < tests.length; i++) {
            System.out.print(tests[i].name);
            long t1 = System.currentTimeMillis();
            tests[i].test(s, size);
            long t2 = System.currentTimeMillis();
            System.out.println(": " +
                    ((double)t2 - t1));
        }
    }
    public static void main(String[] args) {
// Small:
        test(new TreeSet(), 10);
        test(new HashSet(), 10);
// Medium:
        test(new TreeSet(), 100);
        test(new HashSet(), 100);
// Large:
        test(new HashSet(), 100000);
        test(new TreeSet(), 100000);
    }
} ///: