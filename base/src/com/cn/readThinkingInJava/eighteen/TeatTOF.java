package com.cn.readThinkingInJava.eighteen;

import java.io.*;

/**
 * Created by chenqi on 2017/7/12 0012 : 上午 10:39.
 *
 * @version : 1.0
 * @description :DataInputStream用readByte()一次一个字节的读取字符，那么任何字节的值都是合法的，因此不能用返回值来检测输入=是否结束，
 * available（）方法查看还有多少可供存取的字符
 */
public class TeatTOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("G:\\study_hard_myGIT\\javaSE_test\\src\\com\\cn\\readThinkingInJava\\eighteen\\TeatTOF.java")
                )
        );
        while(in.available()!=0)
            System.out.print((char)in.readByte());
        in.close();
    }
}
