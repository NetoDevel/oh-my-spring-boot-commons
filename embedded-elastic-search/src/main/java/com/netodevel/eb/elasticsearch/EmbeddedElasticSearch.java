package com.netodevel.eb.elasticsearch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.IndexSettings;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;
import pl.allegro.tech.embeddedelasticsearch.TypeWithMapping;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

public class EmbeddedElasticSearch implements InitializingBean, DisposableBean {

    private Log log = LogFactory.getLog(EmbeddedElasticSearch.class);

    private EmbeddedElastic embeddedElastic;
    private EmbeddedElasticSearchProperties props;

    public EmbeddedElasticSearch() {
    }

    public EmbeddedElasticSearch(EmbeddedElasticSearchProperties props) {
        this.props = props;
    }

    @Override
    public void destroy() {
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
                .withIndex("debentures", getIndexSettings())
                .withDownloadDirectory(new File("./"))
                .withInstallationDirectory(new File("./"))
                .withCleanInstallationDirectoryOnStop(true)
                .build();

        log.info("Embedded ElasticSearch starting..");
        embeddedElastic.start();
        log.info("Embedded ElasticSearch started. Listening on tcp://127.0.0.1:" + getPort());
    }

    private IndexSettings getIndexSettings() {
        return new IndexSettings(asList(new TypeWithMapping("_doc", props.getMapping())), Optional.of(props.getSetting()));
    }

    public Integer getPort() {
        if (this.props.getPort() != null) return this.props.getPort();
        return 9200;
    }

}
