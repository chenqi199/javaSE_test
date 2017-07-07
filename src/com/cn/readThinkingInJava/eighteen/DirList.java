package com.cn.readThinkingInJava.eighteen;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by chenqi on 2017/7/7 0007 : 下午 3:12.
 *
 * @version : 1.0
 * @description :
 */
public class DirList {
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length==0)
            list=path.list();
//            list = path.list(new DirFilter("."));
        else {
            list = path.list(new DirFilter(".idea"));
        }
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for (String dirItem:
             list) {
            System.out.println(dirItem);
        }
    }
}

class DirFilter implements FilenameFilter{
    private Pattern pattern;
    public DirFilter(String regex){
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}