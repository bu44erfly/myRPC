package com.sam.myRPC.server;

import com.sam.myRPC.common.RPCResponse;
import com.sam.myRPC.service.BlogServiceImpl;
import com.sam.myRPC.service.UserServiceImpl;

public class ServerTest {
    public static void main(String[] args) {
        BlogServiceImpl blogService = new BlogServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();

        int port = 8899 ;
        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1", port);
        serviceProvider.addService(blogService);
        serviceProvider.addService(userService);

    //    ThreadPoolRPCServer RpcServer = new ThreadPoolRPCServer(serviceProvider);

        RPCServer server = new NettyRPCServer(serviceProvider);
        server.start(port);
    }
}
