package com.ganghuan.myRPCVersion0.server;

import com.ganghuan.myRPCVersion0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyRPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("----Server started");
            // BIO的方式监听Socket
            while (true){
                Socket socket = serverSocket.accept();
                // 开启一个线程去处理
                new Thread(()->{
                    try {

                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
                        // 读取客户端传过来的id
                        Integer id = in.readInt() ;
                        User userByUserId = userService.getUserByUserId(id);
                        // 写入User对象给客户端
                        out.writeObject(userByUserId);
                        out.flush();
                    }catch (IOException e){
                        e.printStackTrace();
                        System.out.println("Error reading from IO");
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("server started failed");
        }
    }
}
