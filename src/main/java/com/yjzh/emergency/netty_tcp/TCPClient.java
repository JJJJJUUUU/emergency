package com.yjzh.emergency.netty_tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/10 13:01
 **/
public class TCPClient {
    public static void main(String[] args) {
        try {
            // 连接到服务器
            Socket socket = new Socket("127.0.0.1", 8080);//创建Socket类对象

            System.err.println(socket);
            try {

                DataInputStream in = new DataInputStream(socket
                        .getInputStream());// 读取服务器端传过来信息的DataInputStream

                DataOutputStream out = new DataOutputStream(socket
                        .getOutputStream());// 向服务器端发送信息的DataOutputStream


                Scanner scanner = new Scanner(System.in);// 装饰标准输入流，用于从控制台输入

                while (true) {
                    String send = scanner.nextLine();//读取控制台输入的内容
                    System.out.println("客户端：" + send);//输出键盘输出内容提示 ，也就是客户端向服务器端发送的消息
                    // 把从控制台得到的信息传送给服务器
                    out.writeUTF("客户端：" + send);//将客户端的信息传递给服务器
                    String accpet = in.readUTF();// 读取来自服务器的信息
                    System.out.println(accpet);//输出来自服务器的信息
                }

            } finally {
                socket.close();//关闭Socket监听
            }
        } catch (IOException e) {//捕获异常
            e.printStackTrace();
        }
    }
}
