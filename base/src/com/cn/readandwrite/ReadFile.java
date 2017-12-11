package com.cn.readandwrite;

import java.io.*;

/**
 * Created by chenqi on 2017/4/8 0008.
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
       getFileOutput();
//        System.out.println("11");
    }
    public static String getFileOutput() throws IOException {
        String filePath = ReadFile.class.getClassLoader().getResource("aaa.txt").getPath();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        char[] chars = new char[1024];
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        inputStreamReader.read(chars);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String line;
//        while ((line=bufferedReader.readLine())!=null){
//            System.out.println(line);
//        }
        System.out.println(chars);

        inputStreamReader.close();
        fileInputStream.close();

        return "";

    }

}
