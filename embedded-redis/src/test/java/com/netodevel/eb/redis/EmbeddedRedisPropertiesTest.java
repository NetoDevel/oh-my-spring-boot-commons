package com.netodevel.eb.redis;

import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertEquals;

public class EmbeddedRedisPropertiesTest {

    public static final String PREFIX_PROPS = "embedded.redis.port";

    @Configuration
    @EnableConfigurationProperties(EmbeddedRedisProperties.class)
    static class Config {
    }

    @Test
    public void shouldReturnPort() {
        System.setProperty(PREFIX_PROPS, "6378");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();

        final EmbeddedRedisProperties props = context.getBean(EmbeddedRedisProperties.class);
        assertEquals(Integer.valueOf(6378), props.getPort());
    }

}