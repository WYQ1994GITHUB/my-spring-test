package com.wyq.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author: @luoxin
 * @date: 2021-11-01 16:20
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedisTemplate {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void init() {
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
    }

    @Test
    public void testA() {
        Boolean key1 = redisTemplate.opsForZSet().add("key1", "11", 111);
        Boolean key2 = redisTemplate.opsForZSet().add("key1", "22", 222);
        Boolean key3 = redisTemplate.opsForZSet().add("key1", "33", 333);
        Set<String> rangeResult1 = redisTemplate.opsForZSet().range("key1", 0, -1);
        Double score1 = redisTemplate.opsForZSet().score("key1", "11");
        System.out.println(rangeResult1);
        System.out.println(score1);

        Boolean key4 = redisTemplate.opsForZSet().add("key1", "11", 444);
        Double score2 = redisTemplate.opsForZSet().score("key1", "11");
        Set<String> rangeResult2 = redisTemplate.opsForZSet().range("key1", 0, -1);
        System.out.println(rangeResult2);
        System.out.println(score2);

        Set<String> rangeResult3 = redisTemplate.opsForZSet().range("key1", 0, 0);
        Set<String> rangeResult4 = redisTemplate.opsForZSet().range("key1", 0, 1);
        Set<String> rangeResult5 = redisTemplate.opsForZSet().range("key1", 0, 2);
        Set<String> rangeResult6 = redisTemplate.opsForZSet().range("key1", 0, 3);
        Set<String> rangeResult7 = redisTemplate.opsForZSet().range("key1", 0, 4);
        System.out.println(rangeResult3);
        System.out.println(rangeResult4);
        System.out.println(rangeResult5);
        System.out.println(rangeResult6);
        System.out.println(rangeResult7);
    }
}
