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

- @Decorator @ArcInterFace(interFace:String) 提供远程调用的接口名
- ArcBaseClass 每个远程调用的接口类需要继承的父类
````Java
@ArcInterFace(interFace = "HelloInterFace")
public class Hello extends ArcBaseClass {

    @ArcMethod
    public ret TestRet(@ArcParams("Person") Person p1, @ArcParams("Job")Job j1){
        System.out.println("Job Name is ->"+j1.JobName);
        return ret.success(p1);
    }

    @ArcMethod
    public ret say(String args1,String args2){
        HashMap<String, String> hmp = new HashMap();
        hmp.put("d",args1);
        hmp.put("f",args2);
        ret success = ret.success(hmp);
        return success;
    }
}

````


- @Decorator @ArcMethod
- 定义改方法为 RPC—Method

````java
//  define

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ArcMethod {
}

````

- @Decorator @ArcParams ( ParamsName:String )
- 将特殊的类型注册，在序列化的时候可以自动创建类
- @Decorator @ArcSort (String:sortVal)
- 必须设置为Integer.parseInt后为 int 类型的参数
- 在反序列化创建类的时候自动为成员变量赋值

````java
// define
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.PARAMETER,ElementType.CONSTRUCTOR})
public @interface ArcParams {
    String value();
}

// Hello.java 
// 在定义RPC方法的时候注册相关参数
@ArcInterFace(interFace = "HelloInterFace")
public class Hello extends ArcBaseClass {

    @ArcMethod
    public ret TestRet(@ArcParams("Person") Person p1, @ArcParams("Job")Job j1){
        System.out.println("Job Name is ->"+j1.JobName);
        return ret.success(p1);
    }
}

// Person.java
// 在反序列化时，根据 @ArcSort 给定的参数依次为各个成员变量赋值
// 比如 ["aaa","bbb","ccc","ddd"]
// 则反序列化生成类之后 name = aaa , age = bbb , address = ccc,sex =ddd
@ArcParams("Person")
public class Person extends ArcBaseParams {

    @ArcSort("0")
    public String name;
    @ArcSort("1")
    public String age;
    @ArcSort("2")
    public String address;
    @ArcSort("3")
    public String sex;

    public Person(List list) {
        super(list);
    }
}

````
- nodejs 调用
- run command ->> node index.js
````js
var net = require('net')

let server = net.createConnection({
  'host': "127.0.0.1",
  "port": "9811",
})

server.write(`
[#1]HelloInterFace
[#2]say
[#3]3000
[#4]36
[##]
#a#1#b#2#c#3#z#
[#ENDL#]\n
`, function (err) {
  if (err) {
    throw err
  }
})
    
// 测试TestRet方法的字符串    
`
[#1]HelloInterFace
[#2]TestRet
[#3]3000
[#4]36
[##]
#a##a#tom#b#jump#c#12#d#1#z##b##a#测试#z##c#1#z#
[#ENDL#]\n
`
````
