package com.cn.socket_litian;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 模拟qq聊天功能： 实现客户端与服务器（一对一）的聊天功能，客户端首先发起聊天，输入的内容在服务器端和客户端显示，
 * 然后服务器端也可以输入信息，同样信息也在客户端和服务器端显示
 */

// 服务器类
public class ChatServer {
    private int port = 10086;// 默认服务器端口

    public ChatServer() {
    }

    // 创建指定端口的服务器
    public ChatServer(int port) {
        this.port = port;
    }

    // 提供服务
    public void service() {
        try {// 建立服务器连接
            ServerSocket server = new ServerSocket(port);
            // 等待客户连接
            Socket socket = server.accept();
            try {
                // 读取客户端传过来信息的DataInputStream
                DataInputStream in = new DataInputStream(socket
                        .getInputStream());
                // 向客户端发送信息的DataOutputStream
                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());
                // 获取控制台输入的Scanner
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    // 读取来自客户端的信息
                    String accpet = in.readUTF();
                    System.out.println(accpet);
                    String send = scanner.nextLine();
                    System.out.println("服务器：" + send);
                    // 把服务器端的输入发给客户端
                    out.writeUTF("服务器：" + send);
                }
            } finally {// 建立连接失败的话不会执行socket.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer().service();
    }
}