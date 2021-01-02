package com.netodevel.eb.elasticsearch;

import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertEquals;

public class EmbeddedElasticSearchPropertiesTest {

    @Configuration
    @EnableConfigurationProperties(EmbeddedElasticSearchProperties.class)
    static class Config {
    }

    @Test
    public void shouldReturnPort() {
        System.setProperty("embedded.elasticsearch.port", "9200");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();

        final EmbeddedElasticSearchProperties props = context.getBean(EmbeddedElasticSearchProperties.class);
        assertEquals(Integer.valueOf(9200), props.getPort());
    }

    @Test
    public void shouldReturnPathMappings() {
        System.setProperty("embedded.elasticsearch.mapping", "my-mapping.json");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();

        final EmbeddedElasticSearchProperties props = context.getBean(EmbeddedElasticSearchProperties.class);
        assertEquals("my-mapping.json", props.getMapping());
    }

    @Test
    public void shouldReturnPathSetting() {
        System.setProperty("embedded.elasticsearch.setting", "my-setting.json");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.refresh();

        final EmbeddedElasticSearchProperties props = context.getBean(EmbeddedElasticSearchProperties.class);
        assertEquals("my-setting.json", props.getSetting());
    }

}