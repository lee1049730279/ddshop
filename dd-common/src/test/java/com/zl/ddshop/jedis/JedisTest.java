package com.zl.ddshop.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {
    @Test
    public void testJedis01()
    {
        Jedis jedis = new Jedis("10.31.161.70",6379);
        jedis.set("name","zl");
        System.out.println(jedis.get("name"));
        jedis.close();
    }
    @Test
    public void testJedis3(){
        //创建集群节点集合
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        //将6个节点加入到集合中
        nodes.add(new HostAndPort("10.31.161.70",9001));
        nodes.add(new HostAndPort("10.31.161.70",9002));
        nodes.add(new HostAndPort("10.31.161.70",9003));
        nodes.add(new HostAndPort("10.31.161.70",9004));
        nodes.add(new HostAndPort("10.31.161.70",9005));
        nodes.add(new HostAndPort("10.31.161.70",9006));
        //创建集群对象
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //存入数据
        jedisCluster.set("rrr","zlzl");
        System.out.println(jedisCluster.get("rrr"));
        //关闭连接
        jedisCluster.close();
    }
}
