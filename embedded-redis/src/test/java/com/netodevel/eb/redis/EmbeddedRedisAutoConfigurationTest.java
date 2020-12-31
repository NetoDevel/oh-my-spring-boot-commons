package com.netodevel.eb.redis;

import com.netodevel.helpers.SocketKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertFalse;
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
        System.setProperty("embedded.redis.active", "true");

        context.register(Config.class);
        context.register(EmbeddedRedisAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(6379);
        assertTrue(expected);

    }

    @Test
    public void shouldStartRedisOtherPort() {
        System.setProperty("embedded.redis.port", "6378");
        System.setProperty("embedded.redis.active", "true");


        context.register(Config.class);
        context.register(EmbeddedRedisAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(6378);
        assertTrue(expected);
    }

    @Test
    public void shouldNotStartRedis()  {
        System.setProperty("embedded.redis.active", "false");
        System.setProperty("embedded.redis.port", "6379");

        context.register(Config.class);
        context.register(EmbeddedRedisAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(6379);
        assertFalse(expected);
    }

    @After
    public void tearDown() {
        final EmbeddedRedis redisBean = context.getBean(EmbeddedRedis.class);
        if (redisBean.getRedisServer() != null) {
            redisBean.getRedisServer().stop();
        }
        context.close();
    }

}