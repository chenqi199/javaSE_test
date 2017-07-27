package com.cn.readThinkingInJava.TwentyOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenqi on 2017/7/21 0021 : 上午 10:00.
 *
 * @version : 1.0
 * @description :chache
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec=  Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}
