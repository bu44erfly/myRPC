1. 如何抽象service的方法，这样可以调用不同方法？

  jdk动态代理，client动态代理接口，

  定义Request类保存类信息，参数，参数类型， server通过**反射**调用方法 

  

2. 如何抽象service

  维护一个serviceProvider , 保存接口与实现类的注册关系，server得到接口信息和方法参数后，可以通过反射调用方法

  

3. 服务端如何识别 RPCRequest
    需要提供序列化，反序列化的参数(方法)

​	decode , encode , objectSerialize , jsonSerialize
