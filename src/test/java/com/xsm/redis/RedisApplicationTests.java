package com.xsm.redis;

import cn.tanzhou.support.commons.redis.service.RedisService;
import com.xsm.redis.entity.Student;
import com.xsm.redis.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentService studentService;

    @Test
    public void contextLoads() {
        HashOperations<String, Object, Object> h = redisTemplate.opsForHash();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name", "xushaomin");
        map.put("age", "18");
        h.putAll("user1", map);
        Object o = h.get("user1", "name");
        System.out.println(o);

        redisService.hSet("user2", "name", "xushaomin");
        Map<String, String> user2 = redisService.hGetAll("user2");
        System.out.println(user2);
    }

    // 测试缓存穿透解决方案
    @Test
    public void testCache(){
        Student s1 = studentService.getById(1);
        Student s2 = studentService.getById(1);
        Student s3 = studentService.getById(10);
        Student s4 = studentService.getById(10);
    }

}
