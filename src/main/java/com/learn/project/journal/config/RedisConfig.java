package com.learn.project.journal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
        //By default, RedisTemplate<Object, Object> uses Java's native serialization mechanism,
        // which stores keys and values as binary blobs. That means:
        //
        //Keys are stored as binary — not readable strings
        //
        //Values are stored as serialized Java objects (also binary)



        //-> wsl --install -d Ubuntu
        //-> wsl -d Ubuntu
        //
        //after this ubuntu will start to run and you can run curl and sudo commands
        //
        //curl -fsSL https://packages.redis.io/gpg | sudo gpg --dearmor -o /usr/share/keyrings/redis-archive-keyring.gpg
        //
        //echo "deb [signed-by=/usr/share/keyrings/redis-archive-keyring.gpg] https://packages.redis.io/deb $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/redis.list
        //
        //sudo apt-get update
        //sudo apt-get install redis
        //
        //sudo service redis-server start
        //
        //redis-cli
    }
}
