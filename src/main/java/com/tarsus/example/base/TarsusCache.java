package com.tarsus.example.base;

import com.tarsus.example.decorator.ioc.Inject;
import com.tarsus.example.decorator.ioc.Service;
import redis.clients.jedis.Jedis;

import java.util.Map;

@Service
public class TarsusCache {
    TarsusYaml tarsusYaml = new TarsusYaml();
    public Jedis RedisTemplate = null;

    public TarsusCache() {
        conn();
        setServant();
    }

    public void conn(){
        final Map<String,String> cache = (Map<String, String>) tarsusYaml.servant.get("cache");
        final String url = cache.get("url");
        this.RedisTemplate = new Jedis(url);
        System.out.println("服务正在运行: "+RedisTemplate.ping());
    }

    public void setServant(){
        final String name = (String) tarsusYaml.servant.get("name");
        final String proxy = (String) tarsusYaml.servant.get("proxy");
        System.out.println(name + "  --  " +proxy);
        this.RedisTemplate.sadd(proxy,name);
    }
}
