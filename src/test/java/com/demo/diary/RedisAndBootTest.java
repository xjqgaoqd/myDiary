package com.demo.diary;

import com.demo.diary.common.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisAndBootTest {
    @Autowired
    static RedisTemplate redisTemplate;

    @Autowired
    static RedisUtils redisUtils;

    public static void main(String[] args) {
        //操作不同的类型
//        redisTemplate.opsForValue();//操作字符串类似string
//        redisTemplate.opsForList();//list

        //除了基本的操作，常用方法都可以直接点出来，比如事务和CRUD
        //... ...
        //获取数据库连接相关（比较少用）
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();


        boolean tets = redisUtils.exists("tets");
        System.out.println(tets);
    }
}
