package com.cn.readThinkingInJava.TwentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 1、SingleThreadExecutor就像线程数为1的FixedThreadPool。
 2、如果向SingleThreadExecutor提交多个任务，这些任务将排队。从输出结果可以看到，任务按照提交顺序被执行。
 3、SingleThreadExecutor会序列化所有提交的任务，并维护自己（隐藏）的悬挂任务队列。？？？（不懂）
 4、SingleThreadExecutor可以确保任何线程中都只有唯一的任务在运行。（多个线程使用同一文件系统时，可以用SingleThreadExecutor来保持同步）
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService e = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            e.execute(new LiftOff());

        }
        e.shutdown();
    }
}
/**
 * output:
 * #0(9)  #0(8)  #0(7)  #0(6)  #0(5)  #0(4)  #0(3)  #0(2)  #0(1)  #0(liftoff!)  #1(9)  #1(8)  #1(7)  #1(6)  #1(5)  #1(4)  #1(3)  #1(2)  #1(1)  #1(liftoff!)  #2(9)  #2(8)  #2(7)  #2(6)  #2(5)  #2(4)  #2(3)  #2(2)  #2(1)  #2(liftoff!)  #3(9)  #3(8)  #3(7)  #3(6)  #3(5)  #3(4)  #3(3)  #3(2)  #3(1)  #3(liftoff!)  #4(9)  #4(8)  #4(7)  #4(6)  #4(5)  #4(4)  #4(3)  #4(2)  #4(1)  #4(liftoff!)
 */