package com.cn.readThinkingInJava.TwentyOne;

/**
 * Created by chenqi on 2017/7/26 0026 : 下午 6:12.
 *
 * @version : 1.0
 * @description :输出说明不同的任务在线程切换时被混在了一起，
 * 这种交换是由线程调度器自动控制的， ### 注意： 线程的调度机制是非确定的，多次运行的输出也各不相同
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for Liftoff");
    }
}
/**
 * outPut:
 * #1(9)  Waiting for Liftoff
 #3(9)  #2(9)  #0(9)  #2(8)  #3(8)  #4(9)  #1(8)  #4(8)  #3(7)  #2(7)  #0(8) #2(6)  #3(6)  #4(7)  #1(7)  #4(6)  #3(5)  #2(5)  #0(7)  #2(4)  #3(4)  #4(5)  #1(6)  #4(4)  #3(3)  #2(3)  #0(6)  #2(2)  #3(2)  #4(3)  #1(5)  #4(2)  #3(1)  #2(1)  #0(5)  #2(liftoff!)  #3(liftoff!)  #4(1)  #1(4)  #4(liftoff!)  #0(4)  #1(3)  #0(3)  #1(2)  #0(2)  #1(1)  #0(1)  #1(liftoff!)  #0(liftoff!)
 */
