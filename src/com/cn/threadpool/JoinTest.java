package com.cn.threadpool;

/**
 * Created by 陈奇 on 2017/4/16 0016.
 */
public class JoinTest extends Thread {
    int num = 20;
    JoinTest(String name){
        super(name);
    }
    @Override
    public void run() {
        try {
            sleep(30);
            System.out.println(this.getName()+"____run ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        while(num>0){
//
//            num = this.num--;
//            System.out.println(num);
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new JoinTest("t"));
        Thread t1 = new Thread(new JoinTest("t1"));
        t.start();
        t.join();
        t1.start( );
//        t1.join(20); //main线程等待时间
        t1.join(20); //main线程等待时间
        System.out.println("main end");
    }
}
