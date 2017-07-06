package com.cn.readandwrite;

import java.util.*;

/**
 * Created by 陈奇 on 2017/4/17 0017.
 */
public class StringDemo {
    public static void main(String[] args) {
//        String s = new String("sss");
//        ArrayList arrayList = new ArrayList(21);
//        System.out.println(arrayList.size());

//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        map.put(1, 8);
//        map.put(3, 12);
//        map.put(5, 53);
//        map.put(123, 33);
//        map.put(42, 11);
//        map.put(44, 42);
//        map.put(15, 3);
//        System.out.println(getEntry(map));


//        concatIntToString(22);
        System.out.println(concatIntToString(3002));
        System.out.println(concatIntToString(10009));
        System.out.println(concatIntToString(112));
        System.out.println(concatIntToString(0));
//        concatIntToString(99);
//        concatIntToString(122);
    }

    public static Map.Entry getEntry(Map testmap) {
        Map.Entry entry = null;
        if (testmap == null) return null;
//        Set keyset = testmap.keySet();
//        Object[] os = keyset.toArray();
//        Arrays.sort(os);
        Collection varSet = (Collection) testmap.values();
        Object[] arrVar = varSet.toArray();
        Arrays.sort(arrVar);
        Object o = arrVar[arrVar.length - 1];
        System.out.println(o);
        Set<Map.Entry> entrySet = testmap.entrySet();
        Iterator iteratorEntry = entrySet.iterator();
        while (iteratorEntry.hasNext()) {
            entry = (Map.Entry) iteratorEntry.next();
            if (entry.getValue().equals(o)) return entry;
        }

        System.out.println(Arrays.toString(arrVar));
//        Set<Map.Entry> ens = testmap.entrySet();
//        Map.Entry[] arren = (Map.Entry[]) ens.toArray();
//        Arrays.sort(arren,);

        return entry;
    }

    //    将1-99转换为一到九十九
    public static String concatIntToString(int a) {
        String[] str = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "百", "千","万","十万"};
        if (a < 10) {
            return str[a];
        }
        StringBuffer sbf = new StringBuffer();

        String strNum = String.valueOf(a);
        System.out.println(strNum.length());
        for (int i = 0, j=strNum.length(); i<strNum.length() ; i++,j--) {
            String number = str[Integer.valueOf(String.valueOf(strNum.charAt(i)))];
            if(!sbf.toString().endsWith("零")||!number.equals("零")){

                sbf.append(number);
            }

            if (i< strNum.length()-1&&!number.equals("零")) {
                sbf.append(str[8 + j]);
            }

        }
        return sbf.toString();


    }

}
