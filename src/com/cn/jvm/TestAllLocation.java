package com.cn.jvm;

/**
 * 描述：查看jvm gc
 *
 * jvm params:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:G:\study_hard_myGIT\javaSE_test\src\com\cn\jvm\logs\gc.log
 * @author chen_q_i@163.com
 * 2017/11/7 : 15:44.
 * @version : 1.0
 */
public class TestAllLocation {

    private  static final int ONE_MB=1024*1024;
    
    public static void testAllLocatonMethod(){
        byte[] allLocation,allLocation1,allLocation2,allLocation3;
//        allLocation= new byte[2*ONE_MB];
//        allLocation1= new byte[2*ONE_MB];
//        allLocation2= new byte[2*ONE_MB];
        //GC
        allLocation3= new byte[6*ONE_MB];
    }
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <1 ; i++) {


            testAllLocatonMethod();
        }
        Thread.sleep(1000*60*30);
    }
    
    
    
}
