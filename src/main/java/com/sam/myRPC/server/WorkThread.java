package com.sam.myRPC.server;

import com.sam.myRPC.common.RPCRequest;
import com.sam.myRPC.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

@AllArgsConstructor
public class WorkThread implements Runnable{
    private Socket socket  ;
    private ServiceProvider serviceProvider ;

    @Override
    public void run() {
        try {
            ObjectInputStream  in = new ObjectInputStream(socket.getInputStream()) ;
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()) ;

             RPCRequest req = (RPCRequest) in.readObject() ;
             RPCResponse response = Request(req) ;
             out.writeObject(response) ;
             out.flush();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private RPCResponse Request(RPCRequest req){
        //从客户端传来的接口名
         String interfaceName= req.getInterfaceName() ;
         // 查找接口的实现类
         Object serviceImpl =  serviceProvider.getService(interfaceName) ;

         //执行方法，返回结果
        try {
            Method method = serviceImpl.getClass().getMethod(req.getMethodName() ,
                    req.getParamsTypes());
            Object res = method.invoke(serviceImpl ,req.getParams()) ;
            return  RPCResponse.success(res) ;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
