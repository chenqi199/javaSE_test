package com.ddwx.utils.security;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @version : 1.0
 * @description :
 * Created by  chen_q_i@163.com on 2017/9/7 : 13:52.
 */
public class ServerIPAddress {
    public ServerIPAddress() {

    }

    public static InetAddress getInetAddress(){
        try{
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    return ip;
                }
            }
        }catch(Exception e){
            System.out.println("unknown host!");
        }
        return null;

    }

    public static String getHostIp(InetAddress netAddress){
        if(null == netAddress){
            return null;
        }
        String ip = netAddress.getHostAddress(); //get the ip address
        return ip;
    }

    public static String getHostName(InetAddress netAddress){
        if(null == netAddress){
            return null;
        }
        String name = netAddress.getHostName(); //get the host address
        return name;
    }

}
