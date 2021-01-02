package com.netodevel.eb.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableConfigurationProperties(EmbeddedElasticSearchProperties.class)
public class EmbeddedElasticSearchAutoConfiguration {

    @Autowired
    private EmbeddedElasticSearchProperties props;

    @Bean
    public EmbeddedElasticSearch embeddedElasticSearch() {
        return new EmbeddedElasticSearch(props);
    }

}


