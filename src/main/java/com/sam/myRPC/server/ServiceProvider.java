package com.sam.myRPC.server;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {
    Map<String ,Object> serviceMap;

    public ServiceProvider() { serviceMap = new HashMap<>(); }

    Object getService(String serviceName) {
        return serviceMap.get(serviceName);
    }
    void addService(Object service) {
        Class<?>[] list=  service.getClass().getInterfaces() ;

        for(Class<?> cls : list) {
            serviceMap.put(cls.getName(),service);
        }
    }
}
