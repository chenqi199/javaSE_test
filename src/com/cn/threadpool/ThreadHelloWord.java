package com.cn.threadpool;

/**
 * Created by 陈奇 on 2017/4/14 0014.
 */
public class ThreadHelloWord extends Thread {
    private String helloword;
    ThreadHelloWord(){}
    ThreadHelloWord(String helloword){
       super(helloword);
    }
    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 10) {
                this.yield();
            }
        }
    }

    public static void main(String[] args) {
        ThreadHelloWord h = new ThreadHelloWord("h");
        ThreadHelloWord w = new ThreadHelloWord("w");
        Thread th = new Thread(h);
        Thread tw = new Thread(w);
        th.start();
        tw.start();

    }
}
