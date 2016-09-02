package com.lqzj.core.redis.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class StringRedisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public void save(String key, String value, long timeoutSeconds) {
        if (timeoutSeconds <= 0) {
            valueOperations.set(key, value);
        } else {
            valueOperations.set(key, value, timeoutSeconds, TimeUnit.SECONDS);
        }
        logger.info("cached to redis, key: [{}], expired seconds: [{}]", key, timeoutSeconds);
    }

    public String get(String key) {
        logger.debug("get value from redis, key: [{}]", key);
        return valueOperations.get(key);
    }

    public <T> T get(String key, TypeReference<T> typeReference) {
        String value = get(key);
        try {
            logger.debug("get value from redis, key: [{}]", key);
            return Strings.isNullOrEmpty(value) ? null : objectMapper.readValue(value, typeReference);
        } catch (IOException exception) {
            logger.warn("parse value to object error, value: {}, type: {}, exception: {}", value,
                    typeReference.getType(), exception);
            throw new RuntimeException(exception);
        }
    }

    public void delete(String key) {
        valueOperations.getOperations().delete(key);
        logger.info("delete from redis, key: [{}]", key);
    }
}