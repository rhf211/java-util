package com.example.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
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
       /* config.useClusterServers()
                .addNodeAddress("redis://49.235.66.54:53379")
                .addNodeAddress("redis://49.235.66.54:53380")
                .addNodeAddress("redis://49.235.66.54:53381")
                .addNodeAddress("redis://49.235.66.54:53389")
                .addNodeAddress("redis://49.235.66.54:53390")
                .addNodeAddress("redis://49.235.66.54:53391")
                .setPassword("123456");*/
        config.useSingleServer()
                .setAddress(redissonAddress)
                .setPassword(password);
        config.setCodec(StringCodec.INSTANCE);
        return Redisson.create(config);
    }

}

