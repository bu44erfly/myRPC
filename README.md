1. 服务端如何识别 RPCRequest  
Netty 的解码器 ObjectDecoder：  
在服务端的 NettyServerInitializer 中，配置了 ObjectDecoder。这个解码器基于 Java 对象的序列化机制（通常使用 Serializable 接口）来反序列化字节流为特定的 Java 对象。  
在 ObjectDecoder 中，会检查字节流中包含的类名，使用 Class.forName() 反射来加载对应的类。这就是为什么当服务端接收到字节流时，能够将其识别为 RPCRequest 对象。  

pipeline.addLast(new ObjectDecoder(new ClassResolver() {  
    @Override  
    public Class<?> resolve(String className) throws ClassNotFoundException {  
        return Class.forName(className);  // 动态加载类  
    }  
}));  

2.
