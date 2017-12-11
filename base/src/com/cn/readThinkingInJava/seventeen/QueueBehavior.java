package com.cn.readThinkingInJava.seventeen;

import com.cn.readThinkingInJava.net.mindview.util.Generator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by 陈奇 on 2017/7/8 0008.
 * 这是一个队列的demo，演示队列先进先出的特性
 */
public class QueueBehavior {
    private static int count =10;
    static <T> void test(Queue<T> queue , Generator<T> gen){
        for (int i=0;i<count;i++)
            queue.offer((gen.next()));
        while(queue.peek()!=null)
            System.out.print(queue.remove()+" ");
        System.out.println("-------------------------------------------");
    }
    static class Gen implements Generator<String>{
        String[] s = ("one tow three four five six seven eight night ten").split(" ");
        int i;

        @Override
        public String next() {
            return s[i++];
        }
    }

    public static void main(String[] args) {
        test(new LinkedList<String>(),new Gen());
        test(new PriorityQueue<String>(),new Gen());
        test(new ArrayBlockingQueue<String>(count),new Gen());
        test(new ConcurrentLinkedQueue<String>(),new Gen());
        test(new LinkedBlockingDeque<String>(),new Gen());
        test(new PriorityBlockingQueue<String>(),new Gen());

    }
}
