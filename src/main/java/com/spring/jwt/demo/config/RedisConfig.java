//package com.spring.jwt.demo.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spring.jwt.demo.dto.CustomResponseDto;
//import io.lettuce.core.dynamic.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
//@Configuration
//@EnableRedisRepositories
//public class RedisConfig extends CachingConfigurerSupport {
//
//
//
//    private String redisHost="localhost";
//
//    private int redisPort=6379;
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return redisTemplate;
//    }
////    @Bean
////    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
////        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisConnectionFactory)
////                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
////                        .entryTtl(Duration.ofSeconds(3600))) // varsayılan cache süresi
////                .build();
////        return redisCacheManager;
////    }
////
////    @Bean
////    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
////        RedisTemplate<String, Object> template = new RedisTemplate<>();
////        template.setConnectionFactory(redisConnectionFactory);
////        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
////        template.setKeySerializer(new StringRedisSerializer());
////        template.setHashKeySerializer(new StringRedisSerializer());
////        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
////        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
////        return template;
////    }
//}
