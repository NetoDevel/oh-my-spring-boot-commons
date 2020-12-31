package com.netodevel.eb.elasticsearch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class EmbeddedElasticSearch implements InitializingBean, DisposableBean {

    private Log log = LogFactory.getLog(EmbeddedElasticSearch.class);

    private EmbeddedElastic embeddedElastic;

    @Override
    public void destroy() throws Exception {
        embeddedElastic.stop();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        embeddedElastic = EmbeddedElastic.builder()
                .withElasticVersion("6.8.0")
                .withSetting(PopularProperties.HTTP_PORT, getPort())
                .withSetting(PopularProperties.CLUSTER_NAME, "es_cluster")
                .withEsJavaOpts("-Xms128m -Xmx512m")
                .withSetting("cluster.routing.allocation.disk.threshold_enabled", false)
                .withStartTimeout(2, TimeUnit.MINUTES)
                /*.withIndex("debentures", IndexSettings.builder()
                        .withSettings(getSystemResourceAsStream("debentures-settings.json"))
                        .withType("_doc", getSystemResourceAsStream("debentures-mapping.json"))
                        .build())*/
                .withDownloadDirectory(new File("./"))
                .withInstallationDirectory(new File("./"))
                .withCleanInstallationDirectoryOnStop(true)
                .build();

        log.info("Embedded ElasticSearch starting..");
        embeddedElastic.start();
        log.info("Embedded ElasticSearch started. Listening on tcp://127.0.0.1:" + getPort());
    }

    public Integer getPort() {
        return 9200;
    }

}
