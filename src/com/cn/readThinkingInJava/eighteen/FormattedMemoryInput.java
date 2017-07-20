package com.cn.readThinkingInJava.eighteen;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Created by chenqi on 2017/7/12 0012 : 上午 10:26.
 *
 * @version : 1.0
 * @description :格式化的内存输入， 要读取格式化的数据，可以使用DataInputStream, 它是一个面向字节的i/O类（不是面向字符的）。
 * 因此必须使用InputStream 类，InputStream可以以字节的形式读取任何数据（例如：压缩文件）  本例读取的是字符串
 *
 */
public class FormattedMemoryInput {
    public static void main(String[] args) {
        try {

            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            BufferedInputFile.read("G:\\study_hard_myGIT\\javaSE_test\\src\\com\\cn\\readThinkingInJava\\eighteen\\FormattedMemoryInput.java").getBytes()
                    )
            );
            while (true)
                System.out.print((char) in.readByte());
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
