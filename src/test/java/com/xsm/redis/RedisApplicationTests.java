package com.xsm.redis;

import cn.tanzhou.support.commons.redis.service.RedisService;
import com.alibaba.fastjson.JSON;
import com.xsm.redis.entity.Student;
import com.xsm.redis.entity.User;
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
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private StudentService studentService;

    @Test
    public void contextLoads() {
        HashOperations<String, Object, Object> h = stringRedisTemplate.opsForHash();
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

    /**
     * 测试发布订阅
     * @see {com.xsm.redis.config.MyRedisConfig}
     */
    @Test
    public void testTopic() {
        String channel1 = "fullDataUpload";
        String channel2 = "analysis";
        User user = new User();
        user.setPhone("18675830623");
        user.setName("刘大");

        User user2 = new User();
        user2.setPhone("17856232365");
        user2.setName("李二");
        redisService.convertAndSend(channel1,JSON.toJSONString(user));
        redisService.convertAndSend(channel2,JSON.toJSONString(user2));
    }

}
