package com.cn.readThinkingInJava.TwentyOne;

/**
 * Created by chenqi on 2017/7/26 0026 : 下午 6:08.
 *
 * @version : 1.0
 * @description :Thread 构造器只要一个Runnable对象。调用Thread的start方法为该线程执行必要的初始化操作，创建一个新的线程。
 * 然后调用runnable的run方法，在新的线程中启动该任务
 */
public class BassicThread  {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());

        t.start();
        System.out.println("Waiting for LifftOff");//因为liftOff.run()是由不同的线程执行的，主线程会继续向下执行其他操作，cpu频繁切换，看上去好像是同时执行的
    }
}
