package com.sam.myRPC.client;

import com.sam.myRPC.common.RPCRequest;
import com.sam.myRPC.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SimpleRpcClient implements RPCClient {
    private String host ;
    private int port ;

    @Override
    public RPCResponse sendRequest(RPCRequest request) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println(request);

            outputStream.writeObject(request);
            outputStream.flush();

             RPCResponse res = (RPCResponse)inputStream.readObject() ;
             return res ;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
