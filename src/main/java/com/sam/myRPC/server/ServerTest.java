package com.sam.myRPC.server;

import com.sam.myRPC.common.RPCResponse;

public class ServerTest {
    public static void main(String[] args) {
        BlogServiceImpl blogService = new BlogServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.addService(blogService);
        serviceProvider.addService(userService);

        ThreadPoolRPCServer RpcServer = new ThreadPoolRPCServer(serviceProvider);
        RpcServer.start(8899);
    }
}
