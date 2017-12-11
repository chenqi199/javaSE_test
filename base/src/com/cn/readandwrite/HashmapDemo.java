package com.cn.readandwrite;

import java.io.*;
import java.util.HashMap;

/**
 * Created by 陈奇 on 2017/4/24 0024.
 */
public class HashmapDemo {
    public static void main(String[] args) throws IOException {
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("djaj","djask");
        hashMap.put("aaa","bbb");
        hashMap.put("ccc","ddd");

//        Iterator<Map.Entry> iterator = hashMap.entrySet().iterator();
//        while(iterator.hasNext()){
//            Map.Entry entry =  iterator.next();
//            System.out.println(entry.getKey()+"------------"+entry.getValue());
//        }

//        for ( Map.Entry<String,String> en : hashMap.entrySet()) {
//            System.out.println(en.getKey()+"______________"+en.getValue());
//        }
        String filePath = HashmapDemo.class.getClassLoader().getResource("com/cn/readandwrite/HashmapDemo.class").getPath();
//        String filePath = HashmapDemo.class.getClassLoader().getResource("com/cn/readandwrite/bbb.txt").getPath();
        FileInputStream inputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String text ="";
        while ((text= bufferedReader.readLine())!=null){
            System.out.println(text);
        }
    }
}
