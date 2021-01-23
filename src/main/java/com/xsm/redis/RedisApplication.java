package com.xsm.redis;

import cn.tanzhou.support.commons.redis.annotation.EnableRedis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@EnableRedis
@MapperScan("com.xsm.redis.mapper")
@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


}
