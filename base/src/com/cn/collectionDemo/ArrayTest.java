package com.cn.collectionDemo;

import java.util.Arrays;

/**
 * 描述：
 *
 * @author chen_q_i@163.com
 * 2018/1/26 : 23:08.
 * @version : 1.0
 */
public class ArrayTest {
    public boolean Find(int target, int [][] array) {
        int i= array[0].length-1,j=0;

        while(i>=0&&j<array.length){
            if(array[j][i]<target){
                j++;
            }else if(array[j][i]>target) {
                int[] ints = array[j];
                return Arrays.stream(ints).filter(in -> in == target).count() > 0;

            }
        }
        return false;
    }
}
