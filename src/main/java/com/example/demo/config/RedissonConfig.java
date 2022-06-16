package com.example.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Value(value = "${spring.redis.host}")
    private String redissonAddress;

    @Value(value = "${spring.redis.password}")
    private String password;



    @Bean
    public RedissonClient getRedisClient() {


        Config config = new Config();
        config.useSingleServer()
                .setAddress(redissonAddress)
                .setPassword(password);
        return Redisson.create(config);
    }

}

