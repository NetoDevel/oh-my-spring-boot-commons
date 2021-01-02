package com.netodevel.eb.elasticsearch;

import com.netodevel.helpers.SocketKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmbeddedElasticSearchTest {

    private EmbeddedElasticSearch embeddedElasticSearch;
    private EmbeddedElasticSearchProperties properties;

    @Before
    public void setUp() throws Exception {
        properties = new EmbeddedElasticSearchProperties();
        properties.setPort(9200);
        properties.setMapping("test-mapping.json");
        properties.setSetting("test-setting.json");
        properties.setIndex("any-index");
        properties.setType("_doc");
        properties.setActive(true);

        embeddedElasticSearch = new EmbeddedElasticSearch(properties);
        embeddedElasticSearch.afterPropertiesSet();
    }

    @Test
    public void shouldStartElasticServer() {
        Boolean expected = SocketKit.isPortAlreadyUsed(9200);
        assertTrue(expected);
    }

    @After
    public void tearDown() {
        embeddedElasticSearch.destroy();
    }

}