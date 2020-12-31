package com.netodevel.eb.redis;

import com.netodevel.helpers.SocketKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
    static class Config {
    }

    @Test
    public void shouldStartRedis() {
        context.register(Config.class);
        context.register(EmbeddedRedisAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(6379);
        assertTrue(expected);

    }

    @After
    public void tearDown() {
        context.close();
    }

}