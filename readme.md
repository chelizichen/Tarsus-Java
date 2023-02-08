# Ado-Java 微服务模块

## 整合的仓库
- [Ado-Node](https://github.com/chelizichen/ado-node) 包含 Http 微服务模块 依赖注入 ORM 命令行 等多个开发包的库
- [Ado-Cloud](https://github.com/chelizichen/ado-cloud) 微服务网关 将Http请求代理转发给微服务
- [Ado-Java-Proxy](https://github.com/chelizichen/ado-java-proxy) Spring Boot 服务，可以提供Http 服务，也可以调用微服务
- [Ado-Java-RPC](https://github.com/chelizichen/ado-java-rpc) Java 微服务模块 示例代码
- [Ado-Node-RPC](https://github.com/chelizichen/ado-node-rpc) NodeJs 微服务模块 示例代码


## Doc

- Class ArcBaseServer
- @Decorator ArcServerApplication (port:int)
- 启动Java服务所需的基类和注解
````Java
@ArcServerApplication(port = 9811)
public class ArcServer extends ArcBaseServer {
    public static void main(String[] args) {
        ArcServer c = new ArcServer();
        final Hello hello = new Hello();
        final Test test = new Test();
        c.boost(ArcServer.class);
    }
}

````

- @Decorator @ProxyInterFace(interFace:String) 提供远程调用的接口名
- ArcInterFace 每个远程调用的接口类需要继承的父类
````Java
@ProxyInterFace(interFace = "HelloInterFace")
public class Hello extends ArcInterFace {

    public ret hello(String[] args){
        System.out.println(args);
        for (String s:args){
            System.out.println(s);
        }
        final Object o = JSONObject.toJSONString(args);
        ret success = ret.success(o);
        return success;
    }

    public ret say(String[] args){

        HashMap<String, String> hmp = new HashMap();
        hmp.put("a","1");
        hmp.put("b","2");
        hmp.put("c","3");
        ret success = ret.success(hmp);
        return success;
    }
}

````