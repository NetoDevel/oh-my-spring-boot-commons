package com.netodevel.eb.elasticsearch;

import com.netodevel.helpers.SocketKit;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmbeddedElasticSearchAutoConfigurationTest {

    @Configuration
    @EnableConfigurationProperties(EmbeddedElasticSearchProperties.class)
    static class Config {
    }

    @Test
    public void shoudStartEmbeddedElasitcSearch() {
        System.setProperty("embedded.elasticsearch.mapping", "test-mapping.json");
        System.setProperty("embedded.elasticsearch.setting", "test-setting.json");
        System.setProperty("embedded.elasticsearch.index", "index-name");
        System.setProperty("embedded.elasticsearch.type", "_doc");
        System.setProperty("embedded.elasticsearch.active", "true");


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.register(EmbeddedElasticSearchAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(9200);
        assertTrue(expected);

        context.close();
    }

    @Test
    public void shouldNotStartElasticSearch() {
        System.setProperty("embedded.elasticsearch.mapping", "test-mapping.json");
        System.setProperty("embedded.elasticsearch.setting", "test-setting.json");
        System.setProperty("embedded.elasticsearch.index", "index-name");
        System.setProperty("embedded.elasticsearch.type", "_doc");
        System.setProperty("embedded.elasticsearch.active", "false");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.register(EmbeddedElasticSearchAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(9200);
        assertFalse(expected);

        context.close();
    }

}