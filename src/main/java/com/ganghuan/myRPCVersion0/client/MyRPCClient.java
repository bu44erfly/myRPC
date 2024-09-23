package com.ganghuan.myRPCVersion0.client;

import com.ganghuan.myRPCVersion0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class MyRPCClient {
    public static void main(String[] args) {
        try {
            Socket socket =new Socket("127.0.0.1",8899);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            // 传id
            int num = 99 ;
            out.writeInt(num);
            out.flush();

            //拿到对象
            User user = (User)in.readObject() ;
            System.out.println(user);


            // 建立Socket连接
//            Socket socket = new Socket("127.0.0.1", 8899);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//            // 传给服务器id
//            objectOutputStream.writeInt(new Random().nextInt());
//            objectOutputStream.flush();
//            // 服务器查询数据，返回对应的对象
//            Object o = objectInputStream.readObject();
//            User user = (User) o;
//            System.out.println("服务端返回的User:"+user);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }
    }
}
