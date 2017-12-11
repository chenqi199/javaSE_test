package com.cn.readThinkingInJava.eighteen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by chenqi on 2017/7/12 0012 : 上午 9:37.
 *
 * @version : 1.0
 * @description :18.6.1 缓冲输入文件 想要打开一个文件用于字符输入，可以使用String或者file对象作为文件名的
 * FileInputReader。为离提高速度，对文件进行缓冲，将产生的引用传递给BufferedReader
 */
public class BufferedInputFile {
    public static String read(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder sb = new StringBuilder();
        while((s=in.readLine())!=null)
            sb.append(s+"\n");
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(read("G:\\study_hard_myGIT\\javaSE_test\\src\\com\\cn\\readThinkingInJava\\eighteen\\BufferedInputFile.java"));
    }
}
