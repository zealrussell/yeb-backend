package com.zeal.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * WHAT THE ZZZZEAL
 *
 * @author zeal
 * @version 1.0
 * @since 2023/7/13 11:59
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        key的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        value序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        hash类型key的序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        hash类型value的序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        //注入连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
