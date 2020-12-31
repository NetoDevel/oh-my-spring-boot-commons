package com.netodevel.eb.redis;

import com.netodevel.helpers.SocketKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertTrue;

public class EmbeddedRedisAutoConfigurationTest {

    private AnnotationConfigApplicationContext context;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext();
    }

    @Configuration
    @EnableConfigurationProperties(EmbeddedRedisProperties.class)
    static class Config {
    }

    @Test
    public void shouldStartRedis() {
        System.setProperty("embedded.redis.port", "6379");

        context.register(Config.class);
        context.register(EmbeddedRedisAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(6379);
        assertTrue(expected);

    }

    @Test
    public void shouldStartRedisOtherPort() throws InterruptedException {
        System.setProperty("embedded.redis.port", "6378");

        context.register(Config.class);
        context.register(EmbeddedRedisAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(6378);
        assertTrue(expected);
    }

    @After
    public void tearDown() {
        final EmbeddedRedis redisBean = context.getBean(EmbeddedRedis.class);
        redisBean.getRedisServer().stop();
        context.close();
    }

}