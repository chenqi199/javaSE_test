package com.cn.threadpool.threadSetPriority;

public class HardJobTest {

    public static void main(String[] args) {
        //给主线程设置最高优先级，以保证shutdown命令能按时发出去。
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        //创建一个线程数组
        HardJob arrJob[] = new HardJob[8];
        for (int i = 0; i < arrJob.length; i++) {
            arrJob[i] = new HardJob("job" + String.valueOf(i + 1));

            //给各个线程分别设置优先级，可根据需要进行修改。
            arrJob[i].setPriority(i+1 );
        }

        for (int i = 0; i < arrJob.length; i++) {
            arrJob[i].start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < arrJob.length; i++) {
            arrJob[i].shutdown();
        }

    }

}
