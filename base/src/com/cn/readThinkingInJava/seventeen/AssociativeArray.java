package com.cn.readThinkingInJava.seventeen;

/**

 * @author chen_q_i@163.com on 2017/7/8 0008 : 11:01
 * @since 1.0
 * @param
 */
public class AssociativeArray<K,V> {

    private Object[][] pairs;
    private int index;
    public AssociativeArray(int length){
        pairs = new Object[length][2];
    }
    public void put( K key,V value){
        if (index>=pairs.length)
            throw new ArrayIndexOutOfBoundsException();
        pairs[index++] = new Object[]{key,value};
    }

//    public V get(K key){
//        for (int i =0;i<index;i++){
//            if (key.equals(pairs[i][0])){}
////                return
//        }
//    }
}
