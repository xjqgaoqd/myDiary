//package com.demo.diary;
//
//import com.alibaba.fastjson.JSONObject;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Transaction;
//
//public class JedisTest {
//    public static void main(String[] args) {
//        //连接jedis
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//        //jedis.的查看redis手册
//        //System.out.println(jedis.ping());
//        //System.out.println(jedis.flushDB());
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("hello","world");
//        jsonObject.put("name","xjq");
//        String s = jsonObject.toJSONString();
//
//        jedis.watch(s);//cas锁住
//
//        //事务
//        Transaction multi = jedis.multi();
//        multi.set("user1",s);
//        multi.set("user2",s);
//        multi.exec();
//
//        System.out.println(jedis.get("user1"));
//        //关闭连接
//        jedis.close();
//    }
//}
