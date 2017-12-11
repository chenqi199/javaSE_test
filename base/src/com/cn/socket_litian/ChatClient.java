package com.cn.socket_litian;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 注意用到的输入输出流DataInputStream和DataOutputStream，成对出现，最好用字节流
 */
// 客户端类
public class ChatClient {
    private String host = "192.168.43.10";// 默认连接到本机
    private int port = 10086;// 默认连接到端口8189

    public ChatClient() {

    }

    // 连接到指定的主机和端口
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void chat() {
        try {
            // 连接到服务器
            Socket socket = new Socket(host, port);

            try {
                // 读取服务器端传过来信息的DataInputStream
                DataInputStream in = new DataInputStream(socket
                        .getInputStream());
                // 向服务器端发送信息的DataOutputStream
                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());

                // 装饰标准输入流，用于从控制台输入
                Scanner scanner = new Scanner(System.in);

                while (true) {
                    String send = scanner.nextLine();
                    System.out.println("客户端：" + send);
                    // 把从控制台得到的信息传送给服务器
                    out.writeUTF("客户端：" + send);
                    // 读取来自服务器的信息
                    String accpet = in.readUTF();
                    System.out.println(accpet);
                }

            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient().chat();
    }
}