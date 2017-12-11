package com.cn.readThinkingInJava.eighteen;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by chenqi on 2017/7/12 0012 : 上午 9:52.
 *
 * @version : 1.0
 * @description : 从BufferedInputFile.read()读取的String结果被用来创建一个StringReader。然后调用read（）每次读取一个字符，并发送到控制台
 */
public class MemoryInput {

    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read("G:\\study_hard_myGIT\\javaSE_test\\src\\com\\cn\\readThinkingInJava\\eighteen\\MemoryInput.java"));
        int c;
        while ((c=in.read())!=-1)
            System.out.print(c);
//            System.out.print((char)c);


        in.close();
    }


}
