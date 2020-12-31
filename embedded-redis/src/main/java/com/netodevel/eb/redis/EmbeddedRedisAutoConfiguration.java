package com.netodevel.eb.redis;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class EmbeddedRedisAutoConfiguration {

    @Bean
    public EmbeddedRedis embeddedRedis() {
        return new EmbeddedRedis();
    }
}
