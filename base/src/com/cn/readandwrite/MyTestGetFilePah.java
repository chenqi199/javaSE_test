package com.cn.readandwrite;

import java.io.*;

/**
 * Created by chenqi on 2017/4/7 0007.
 */
public class MyTestGetFilePah {
    public static void main(String[] args) throws IOException {
        getFilePath();
    }


    public static String getFilePath() throws IOException {
        String springconfig = MyTestGetFilePah.class.getClassLoader().getResource("abcd.txt").getPath();
//        String springcon = MyTestGetFilePah.class.getClassLoader().getResource("").getPath();
//        System.out.println(springconfig);
//        System.out.println(springcon);
        InputStreamReader inputStreamReader = new InputStreamReader((new FileInputStream(springconfig)),"utf-8");
        File file = new File("furck.html");

        if(!file.exists()){
            file.createNewFile();
        }
        System.out.println(file.getAbsolutePath());
        FileWriter fileWriter = new FileWriter(file.getName());
//        FileOutputStream fileOutputStream  = new FileOutputStream();
//            char[] chars = new char[1024*1024];
//        inputStreamReader.read(chars);
//        String file = new String(chars,0,chars.length);
//        System.out.println(file);
//        inputStreamReader.close();
//        return file;
//        =======================================================================
//        BufferedWriter bufferedWriter = new BufferedWriter(fileOutputStream.write(););
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String readline;
        while ((readline=bufferedReader.readLine())!=null){
            readline = "<li>"+readline+"</li>";
            fileWriter.write(readline+"\r\n");

        }
        fileWriter.flush();
        inputStreamReader.close();
        fileWriter.close();
        return "";
//        =======================================================================
//        File file = new File(springconfig);
//        FileInputStream fileInputStream = new FileInputStream(file);
//        byte[] bytes= new byte[(int) file.length()];
//        int len = fileInputStream.read(bytes);
//        String content = new String(bytes,0 ,len,"utf-8");
//        System.out.println(content);
//     return content;
    }

}
