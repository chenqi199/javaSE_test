package com.cn.readThinkingInJava.TwentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description :chache Liftoff对象知道具体的任务，他有单一的要执行的方法，
 * {@link java.util.concurrent.Executor } 来管理 {@link  java.lang.Runnable} executor知道如何恰当的构建上下文来执行Runnable
* cachedThreadPool可以根据需要创建线程，当一个线程结束时并不释放它而是把它回收到池里，以便下次使用
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec=  Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());//为每个任务创建一个线程
        exec.shutdown(); //提交任务
    }
}
/**
 * output
 * #2(9)  #2(8)  #0(9)  #0(8)  #0(7)  #0(6)  #0(5)  #0(4)  #4(9)  #2(7)  #1(9)  #3(9)  #1(8)  #2(6)  #4(8)  #0(3)  #4(7)  #2(5)  #1(7)  #3(8)  #1(6)  #2(4)  #4(6)  #0(2)  #4(5)  #2(3)  #1(5)  #3(7)  #1(4)  #2(2)  #4(4)  #0(1)  #4(3)  #2(1)  #1(3)  #3(6)  #1(2)  #2(liftoff!)  #4(2)  #0(liftoff!)  #4(1)  #1(1)  #4(liftoff!)  #1(liftoff!)  #3(5)  #3(4)  #3(3)  #3(2)  #3(1)  #3(liftoff!)
 */