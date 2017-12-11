package com.cn.readThinkingInJava.TwentyOne;

/**
 * Created by chenqi on 2017/7/20 0020 : 下午 6:07.
 *
 * @version : 1.0
 * @description :
 */
public class MainThread {
    public static void main(String[] args) {

         LiftOff liftOff = new LiftOff();
         liftOff.run();//这个run方法被当做普通方法调用、main线程驱动

        for (int i = 0 ;i<5;i++){
            new Thread(new LiftOff()).start();//产生单独的线程实
        }
    }
}
