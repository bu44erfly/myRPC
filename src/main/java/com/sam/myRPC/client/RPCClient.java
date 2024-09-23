package com.sam.myRPC.client;

import com.sam.myRPC.common.Blog;
import com.sam.myRPC.common.User;
import com.sam.myRPC.service.BlogService;
import com.sam.myRPC.service.UserService;

public class RPCClient {
    public static void main(String[] args) {

        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        // 服务的方法1
        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);
        System.out.println();

//        // 服务的方法2
//        User user = User.builder().userName("Anon Chihaya").id(100).sex(true).build();
//        Integer integer = proxy.insertUserId(user);
//        System.out.println("向服务端插入数据："+integer);
//        System.out.println();

        //Blog
        BlogService blogServiceProxy = clientProxy.getProxy(BlogService.class);
        Blog blog= blogServiceProxy.getBlogById(12) ;
        System.out.println("向服务端插入blog数据："+ blog);
    }
}
