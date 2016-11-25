package com.lqzj.database.config.redis;

import com.lqzj.common.properties.RedisConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig extends RedisAutoConfiguration {

    @Autowired
    private RedisConfigProperties redisConfigProperties;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfigProperties.getMaxIdle());
        poolConfig.setMinIdle(redisConfigProperties.getMinIdle());

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisConfigProperties.getHost());
        jedisConnectionFactory.setPort(redisConfigProperties.getPort());
        jedisConnectionFactory.setPassword(redisConfigProperties.getPassword());
        jedisConnectionFactory.setPoolConfig(poolConfig);
        jedisConnectionFactory.setTimeout(redisConfigProperties.getTimeout());
        jedisConnectionFactory.setUsePool(true);

        return jedisConnectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate restTemplate(JedisConnectionFactory connectionFactory) {
        final RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}