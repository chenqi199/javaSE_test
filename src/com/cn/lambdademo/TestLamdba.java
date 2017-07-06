package com.cn.lambdademo;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by 陈奇 on 2017/5/2 0002.
 */
public class TestLamdba {
    public static void main(String[] args) {
        Integer[] nums = {2, 5, 1, 6};
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1 < o2)
                return -1;
            return 0;
            }

//        }-> {
//            if(o1 < o2)
//                return -1;
//            return 0;
        });
        for (Integer n : nums) {
            System.out.println(n);
        }
    }



}
