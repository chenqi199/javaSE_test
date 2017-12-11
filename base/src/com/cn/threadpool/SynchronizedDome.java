package com.cn.threadpool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 陈奇 on 2017/4/16 0016.
 */
public class SynchronizedDome {

//    public  static final  ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public  static final  ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
//        testThread();

        testLocal();
    }

    private static void testLocal() throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                threadLocal.set("aaaaaaaaaa");
                System.out.println("111 set ok");
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("11111"+threadLocal.get());
            }

        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                threadLocal.set("bbbb");
                System.out.println("222 set ok");
                System.out.println("2222"+threadLocal.get());
            }

        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();



    }

    private static void testThread() {
        Map ticketMap = new HashMap<String, Boolean>();//票池：<票编号,是否已出售>
        for(int i = 1; i <= 100; i++){//生成100张火车票到票池
            ticketMap.put("T" + i, false);
        }

//生成4名售票员
        TicketSaler s1 = new TicketSaler(ticketMap, "S1");
        TicketSaler s2 = new TicketSaler(ticketMap, "S2");
        TicketSaler s3 = new TicketSaler(ticketMap, "S3");
        TicketSaler s4 = new TicketSaler(ticketMap, "S4");

        Thread t1 =new Thread(s1);
        Thread t2 =new Thread(s2);
        Thread t3 =new Thread(s3);
        Thread t4 =new Thread(s4);

        t1.setName("T1");
        t2.setName("T2");
        t3.setName("T3");
        t4.setName("T4");

        ExecutorService service = Executors.newCachedThreadPool();

        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.execute(t4);

        service.shutdown();//执行完线程池中的线程后尽快退出
    }
}
class TicketSaler implements Runnable {
    private Map<String, Boolean> ticketMap;//票池
    private String salerName;//售票员姓名

    public TicketSaler(Map<String, Boolean> ticketMap, String salerName){
        this.ticketMap = ticketMap;
        this.salerName = salerName;
    }


    //售票
    private void saleTicket(){
        int rigter = 100;
        for(Iterator<String> it = ticketMap.keySet().iterator(); ;){
            synchronized (ticketMap)
            {
                if(it.hasNext()){
                    String ticketNo = it.next();
                    if(!ticketMap.get(ticketNo)){
                        System.out.println(rigter--);
                        System.out.println(salerName +":" + ticketNo + "已被售出。");
                        ticketMap.put(ticketNo, true);
                    }
                }else{
                    System.out.println("没票了！！！！");
                    break;
                }
            }
        }
    }
    @Override
    public void run() {
        saleTicket();
    }

}

