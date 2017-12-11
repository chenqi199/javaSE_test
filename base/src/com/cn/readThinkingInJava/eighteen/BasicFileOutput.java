package com.cn.readThinkingInJava.eighteen;

import java.io.*;

/**
 * Created by chenqi on 2017/7/12 0012 : 上午 10:59.
 *
 * @version : 1.0
 * @description :18.6.4
 */
public class BasicFileOutput {
    static String file = "BasicFileOutput.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("G:\\study_hard_myGIT\\javaSE_test\\src\\com\\cn\\readThinkingInJava\\eighteen\\BasicFileOutput.java")
                )
        );
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file))
        );
        int lineCount=1;
        String s ;
        while((s=in.readLine())!=null)
            out.println(lineCount++ +":"+ s);
        out.close();
        System.out.println(BufferedInputFile.read(file));



    }
}
