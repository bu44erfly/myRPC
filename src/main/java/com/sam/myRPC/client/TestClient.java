package com.sam.myRPC.client;

import com.sam.myRPC.common.Blog;
import com.sam.myRPC.common.User;
import com.sam.myRPC.service.BlogService;
import com.sam.myRPC.service.UserService;

public class TestClient {
    public static void main(String[] args) {
        // 构建一个使用java Socket/ netty/....传输的客户端
        RPCClient rpcClient = new NettyRPCClient("127.0.0.1", 8899);
        // 把这个客户端传入代理客户端

        RPCClientProxy clientProxy = new RPCClientProxy(rpcClient);
        UserService userService= clientProxy.getProxy(UserService.class); ;

        // 调用方法
        User userByUserId = userService.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);

        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("向服务端插入数据："+integer);

        BlogService blogService = clientProxy.getProxy(BlogService.class);

        Blog blogById = blogService.getBlogById(10000);
        System.out.println("从服务端得到的blog为：" + blogById);
    }
}
