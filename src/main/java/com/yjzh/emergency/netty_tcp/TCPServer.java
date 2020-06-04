package com.yjzh.emergency.netty_tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/10 13:38
 **/
public class TCPServer {
    private int port = 8080;// 默认服务器端口

    public TCPServer() {

    }

    // 创建指定端口的服务器
    public TCPServer(int port) {//构造方法
        this.port = port;//将方法参数赋值给类参数
    }

    // 提供服务
    public void service() {//创建service方法
        try {// 建立服务器连接
            ServerSocket server = new ServerSocket(port);//创建  ServerSocket类
            Socket socket = server.accept();// 等待客户连接
            try {
                DataInputStream in = new DataInputStream(socket
                        .getInputStream());// 读取客户端传过来信息的DataInputStream
                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());// 向客户端发送信息的DataOutputStream
                Scanner scanner = new Scanner(System.in);//从键盘接受数据
                while (true) {
                    String accpet = in.readUTF();// 读取来自客户端的信息
                    System.out.println(accpet);//输出来自客户端的信息
                    String send = scanner.nextLine();//nextLine方式接受字符串
                    System.out.println("服务器：" + send);//输出提示信息
                    out.writeUTF("服务器：" + send);//把服务器端的输入发给客户端
                }
            } finally {// 建立连接失败的话不会执行socket.close();
                socket.close();//关闭连接
                server.close();//关闭
            }
        } catch (IOException e) {//捕获异常
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {//主程序方法
        new TCPServer().service();//调用 service方法
    }
}
