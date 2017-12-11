package com.cn.threadpool.fixedthreadpool;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by 陈奇 on 2017/5/1 0001.
 */
public class TaskCallable implements Callable<String> {
    private int id;
    public TaskCallable(int id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {

        return "result of taskWithResult "+id;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();//工头
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();//
        for(int i = 0 ; i < 10 ;i++){
            results.add(exec.submit(new TaskCallable(i)));//submit返回一个Future，代表了即将要返回的结果

        }
        exec.shutdown();    //停掉线程
        StringBuilder strbu = new StringBuilder();
        for (Future<String> s :
                results) {
            strbu.append(s.get()).append("\r\n");
        }
        System.out.println(strbu.toString());
    }
}
