package com.cn.readThinkingInJava.TwentyOne;

/**
 * Created by chenqi on 2017/7/26 0026 : 下午 6:12.
 *
 * @version : 1.0
 * @description :
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for Liftoff");
    }
}
