package com.cn.threadpool.threadSetPriority;

import java.util.Random;

public class HardJob extends Thread {
    private volatile boolean running = true;

    public HardJob(String name) {
        super(name);
    }

    public void shutdown() {
        running = false;
    }

    public void run() {
        double result = 0;
        int cnt = 0;
        Random randGen = new Random();

        System.out.println(this.getName() + "(Priority " + this.getPriority() + "), begin to run.");
        while (running) {
            result += Math.sin(randGen.nextDouble() * 2 * Math.PI);
            cnt++;
        }
        System.out.println(this.getName() + "(Priority " + this.getPriority() + "), run " + cnt + " times.");
    }
}