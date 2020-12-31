package com.netodevel.eb.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("embedded.redis")
public class EmbeddedRedisProperties {

    private Integer port;
    private Boolean active;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
