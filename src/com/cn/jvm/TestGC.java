package com.cn.jvm;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2017/11/13 : 10:36.
 * @version : 1.0
 */
public class TestGC {
    public static void main(String[] args) {
        {

            byte[] placeholder = new byte[1024 * 1024 * 64];
        }
        int i = 0;
        System.gc();
    }
}
