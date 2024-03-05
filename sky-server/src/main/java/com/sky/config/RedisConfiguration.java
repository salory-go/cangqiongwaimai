package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 项目名: sky-take-out
 * 文件名: RedisConfiguration
 * 创建者: LZS
 * 创建时间:2024/3/5 17:49
 * 描述:
 **/
@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        log.info("开始创建redis模板对象...");
        RedisTemplate redisTemplate = new RedisTemplate();
        //设定redis的连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置string类型的序列化器（图形化界面才能看得懂，不“乱码”）
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
