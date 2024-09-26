package com.sam.myRPC.server;



import java.util.HashMap;
import java.util.Map;

/**
 * 存放服务接口名与服务端对应的实现类
 * 服务启动时要暴露其相关的实现类
 * 根据request中的interface调用服务端中相关实现类
 */
public class ServiceProvider {
    /**
     * 一个实现类可能实现多个服务接口，
     */
    private Map<String, Object> objectMap;

    public ServiceProvider(){
        objectMap = new HashMap<>();
    }

    public void addService(Object service){
        Class<?> list[] =service.getClass().getInterfaces() ;
        for(Class<?> cls : list){
            objectMap.put(cls.getName() , service) ;
        }
    }

    public Object getService(String interfaceName){
        return objectMap.get(interfaceName);
    }
}
