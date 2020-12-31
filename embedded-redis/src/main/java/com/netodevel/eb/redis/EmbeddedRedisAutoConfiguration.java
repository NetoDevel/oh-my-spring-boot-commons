package com.netodevel.eb.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableConfigurationProperties(EmbeddedRedisProperties.class)
public class EmbeddedRedisAutoConfiguration {

    @Autowired
    private EmbeddedRedisProperties props;

    @Bean
    public EmbeddedRedis embeddedRedis() {
        return new EmbeddedRedis(props);
    }
}
