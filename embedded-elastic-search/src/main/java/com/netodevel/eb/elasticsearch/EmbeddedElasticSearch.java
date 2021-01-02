package com.netodevel.eb.elasticsearch;

import com.netodevel.helpers.FileKit;
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

import static java.lang.ClassLoader.getSystemResourceAsStream;
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
        if (getActive()) {
            embeddedElastic.stop();
        }
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
                .withIndex(getIndex(), getIndexSettings())
                .withDownloadDirectory(new File("./"))
                .withInstallationDirectory(new File("./"))
                .withCleanInstallationDirectoryOnStop(true)
                .build();

        if (getActive()) {
            log.info("Embedded ElasticSearch starting..");
            embeddedElastic.start();
            log.info("Embedded ElasticSearch started. Listening on tcp://127.0.0.1:" + getPort());
        }
    }

    private String getIndex() {
        if (props == null || props.getIndex() == null) {
            throw new IllegalArgumentException("index name can not be null");
        }
        return props.getIndex();
    }

    private String getType() {
        if (props == null || props.getType() == null) {
            throw new IllegalArgumentException("index name can not be null");
        }
        return props.getType();
    }

    private IndexSettings getIndexSettings() {
        return new IndexSettings(asList(new TypeWithMapping(getType(), getValueMapping())), Optional.of(getValueSetting()));
    }

    private String getValueSetting() {
        return FileKit.inputStreamToString(getSystemResourceAsStream(props.getSetting()));
    }

    private String getValueMapping() {
        return FileKit.inputStreamToString(getSystemResourceAsStream(props.getMapping()));
    }

    public Integer getPort() {
        if (this.props != null && this.props.getPort() != null) return this.props.getPort();
        return 9200;
    }

    private boolean getActive() {
        return props != null && props.getActive();
    }

    public EmbeddedElastic getEmbeddedElastic() {
        return embeddedElastic;
    }

}
