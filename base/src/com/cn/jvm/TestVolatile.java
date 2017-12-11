package com.cn.jvm;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/16 : 17:39.
 * @version : 1.0
 */
public class TestVolatile {
    private static volatile int a = 0;

    public static void increase() {
        a++;
    }

    private static final int TH_C = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[TH_C];
        for (int i = 0; i < TH_C; i++) {

            threads[i] = new Thread(
                    () -> {
                        for (int j = 0; j < 100; j++) {
                            increase();
                        }
                    });
            threads[i].start();
        }


        while (Thread.activeCount() > 1){

            Thread.yield();
        }

        System.out.println(a);
    }

}
