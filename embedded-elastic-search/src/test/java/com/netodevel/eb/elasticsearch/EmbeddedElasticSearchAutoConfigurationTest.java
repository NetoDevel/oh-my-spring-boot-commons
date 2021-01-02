package com.netodevel.eb.elasticsearch;

import com.netodevel.helpers.SocketKit;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertTrue;

public class EmbeddedElasticSearchAutoConfigurationTest {

    @Configuration
    @EnableConfigurationProperties(EmbeddedElasticSearchProperties.class)
    static class Config {
    }

    @Test
    @Ignore
    public void shoudStartEmbeddedElasitcSearch() {
        System.setProperty("embedded.elasticsearch.mapping", "my-mapping.json");
        System.setProperty("embedded.elasticsearch.setting", "my-setting.json");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Config.class);
        context.register(EmbeddedElasticSearchAutoConfiguration.class);
        context.refresh();

        Boolean expected = SocketKit.isPortAlreadyUsed(9200);
        assertTrue(expected);

        context.close();
    }


}