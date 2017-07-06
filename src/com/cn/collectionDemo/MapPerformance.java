package com.cn.collectionDemo;

/**
 * Created by chenqi on 2017/7/4 0004 : 上午 10:39.
 *
 * @version : 1.0
 * @description :比较三种map的优缺点，
 * Testing java.util.Hashtable size 10
put: 2.0
get: 1.0
iteration: 3.0
Testing java.util.HashMap size 10
put: 1.0
get: 1.0
iteration: 3.0
Testing java.util.TreeMap size 10
put: 3.0
get: 1.0
iteration: 3.0
Testing java.util.Hashtable size 100
put: 3.0
get: 2.0
iteration: 5.0
Testing java.util.HashMap size 100
put: 2.0
get: 3.0
iteration: 9.0
Testing java.util.TreeMap size 100
put: 6.0
get: 5.0
iteration: 2.0
Testing java.util.HashMap size 10000
put: 186.0
get: 142.0
iteration: 213.0
Testing java.util.Hashtable size 10000
put: 154.0
get: 334.0
iteration: 330.0
Testing java.util.TreeMap size 10000
put: 391.0
get: 280.0
iteration: 225.0
 */
import java.util.*;
public class MapPerformance {
    private static final int REPS = 200;
    public static Map fill(Map m, int size) {
        for(int i = 0; i < size; i++) {
            String x = Integer.toString(i);
            m.put(x, x);
        }
        return m;
    }
    private abstract static class Tester {
        String name;
        Tester(String name) { this.name = name; }
        abstract void test(Map m, int size);
    }
    private static Tester[] tests = {
            new Tester("put") {
                void test(Map m, int size) {
                    for(int i = 0; i < REPS; i++) {
                        m.clear();
                        fill(m, size);
                    }
                }
            },
            new Tester("get") {
                void test(Map m, int size) {
                    for(int i = 0; i < REPS; i++)
                        for(int j = 0; j < size; j++)
                            m.get(Integer.toString(j));
                }
            },
            new Tester("iteration") {
                void test(Map m, int size) {
                    for(int i = 0; i < REPS * 10; i++) {
                        Iterator it = m.entrySet().iterator();
                        while(it.hasNext())
                            it.next();
                    }
                }
            },
    };
    public static void test(Map m, int size) {
// A trick to print out the class name:
        System.out.println("Testing " +
                m.getClass().getName() + " size " + size);
        fill(m, size);
        for(int i = 0; i < tests.length; i++) {
            System.out.print(tests[i].name);
            long t1 = System.currentTimeMillis();
            tests[i].test(m, size);
            long t2 = System.currentTimeMillis();
            System.out.println(": " +
                    ((double)(t2 - t1)));
        }
    }
    public static void main(String[] args) {
// Small:
        test(new Hashtable(), 10);
        test(new HashMap(), 10);
        test(new TreeMap(), 10);
// Medium:
        test(new Hashtable(), 100);
        test(new HashMap(), 100);
        test(new TreeMap(), 100);
// Large:
        test(new HashMap(), 10000);
        test(new Hashtable(), 10000);
        test(new TreeMap(), 10000);
    }
} ///: