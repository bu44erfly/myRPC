package com.sam.myRPC.server;

import com.sam.myRPC.common.RPCRequest;
import com.sam.myRPC.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

                        RPCRequest req = (RPCRequest) in.readObject() ;
                        Method method = userService.getClass().getMethod(req.getMethodName(), req.getParamsTypes());
                        Object result = method.invoke( userService ,req.getParams()) ;
                        out.writeObject(RPCResponse.success(result));
                        out.flush();

                    }catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                            InvocationTargetException e){
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
