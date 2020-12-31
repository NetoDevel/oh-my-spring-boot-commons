package com.netodevel.eb.elasticsearch;

import com.netodevel.helpers.SocketKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmbeddedElasticSearchTest {

    private EmbeddedElasticSearch embeddedElasticSearch;

    @Before
    public void setUp() throws Exception {
        embeddedElasticSearch = new EmbeddedElasticSearch();
        embeddedElasticSearch.afterPropertiesSet();
    }

    @Test
    public void shouldStartElasticServer() {
        Boolean expected = SocketKit.isPortAlreadyUsed(9200);
        assertTrue(expected);
    }

    @After
    public void tearDown() throws Exception {
        embeddedElasticSearch.destroy();
    }

}