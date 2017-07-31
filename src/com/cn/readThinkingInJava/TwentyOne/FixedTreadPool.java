package com.cn.readThinkingInJava.TwentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService
 真正的线程池接口。
 ScheduledExecutorService
 能和Timer/TimerTask类似，解决那些需要任务重复执行的问题。
 ThreadPoolExecutor是ExecutorService的默认实现。
 ScheduledThreadPoolExecutor继承ThreadPoolExecutor的ScheduledExecutorService接口实现，周期性任务调度的类实现。

 * 在Executors类里面提供了一些静态工厂，生成一些常用的线程池。
 * 2.newFixedThreadPool
 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程
 */
public class FixedTreadPool {
    public static void main(String[] args) {
        ExecutorService executorService  = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
