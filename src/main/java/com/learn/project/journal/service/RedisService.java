package com.learn.project.journal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;//enable redis db access

    public <T> T getKey(String key, Class<T> type) {
        Object json = redisTemplate.opsForValue().get(key);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json.toString(), type);
        } catch (JsonProcessingException | NullPointerException e) {
            log.error(e.toString());
            return null;
        }
    }

    public void putKey(String key, Object o, Long ttl) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, s, ttl, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
        }


    }

}
