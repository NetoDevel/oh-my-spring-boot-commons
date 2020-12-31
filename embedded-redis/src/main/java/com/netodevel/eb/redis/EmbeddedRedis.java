package com.netodevel.eb.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import redis.embedded.RedisServer;

import java.io.IOException;

public class EmbeddedRedis implements InitializingBean, DisposableBean {

    private Log log = LogFactory.getLog(EmbeddedRedis.class);

    private RedisServer redisServer;
    private EmbeddedRedisProperties props;
    private Boolean isRunning;

    public EmbeddedRedis(EmbeddedRedisProperties props) {
        this.props = props;
        isRunning = false;
    }

    @Override
    public void destroy() {
        try {
            if (isRunning) {
                log.info("Redis Exiting...");
                redisServer.stop();
                isRunning = false;
            }
        } catch (Exception e) {
            log.error("Error when stop redis, error = " + e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() {
        if (isDisabled()) {
            return;
        }
        try {
            this.redisServer = new RedisServer(getPort());
        } catch (IOException e) {
            log.error("Error when starting redis, error = " + e.getMessage());
        }
        redisServer.start();
        isRunning = true;
        log.info("Redis started. Listening on tcp://127.0.0.1:" + getPort());
    }

    private boolean isDisabled() {
        return Boolean.FALSE.equals(props.getActive());
    }

    public Integer getPort() {
        if (props.getPort() != null) return props.getPort();
        return 6379;
    }

    public RedisServer getRedisServer() {
        return redisServer;
    }
}
