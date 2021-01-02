package com.netodevel.eb.elasticsearch;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("embedded.elasticsearch")
public class EmbeddedElasticSearchProperties {

    private Integer port;
    private String mapping;
    private String setting;
    private String index;
    private String type;
    private Boolean active;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
