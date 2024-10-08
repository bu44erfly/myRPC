package com.sam.myRPC.service;


import com.sam.myRPC.common.User;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        // 模拟从数据库中取用户的行为
        User user = User.builder().id(id).userName("taki").sex(true).build();
        System.out.println("客户端查询了"+id+"用户");
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功："+user);
        return 1;
    }
}
