package com.cn.readThinkingInJava.TwentyOne;

/**
 * Created by chenqi on 2017/7/20 0020 : 下午 6:07.
 *
 * @version : 1.0
 * @description :
 */
public class MainThread {
    public static void main(String[] args) {

        for (int i = 0 ;i<5;i++){

            new Thread(new LiftOff()).start();

        }
    }
}
