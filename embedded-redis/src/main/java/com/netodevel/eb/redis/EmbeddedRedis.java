package com.netodevel.eb.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import redis.embedded.RedisServer;

import java.io.IOException;

public class EmbeddedRedis implements InitializingBean {

    private Log log = LogFactory.getLog(EmbeddedRedis.class);

    private RedisServer redisServer;
    private EmbeddedRedisProperties props;

    public EmbeddedRedis(EmbeddedRedisProperties props) {
        this.props = props;
    }

    public void destroy() {
        log.info("Redis Exiting...");
        try {
            redisServer.stop();
        } catch (Exception e) {
            log.error("Error when stop redis, error = " + e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() {
        try {
            this.redisServer = new RedisServer(getPort());
        } catch (IOException e) {
            log.error("Error when starting redis, error = " + e.getMessage());
        }
        redisServer.start();
        log.info("Redis started. Listening on tcp://127.0.0.1:" + getPort());

        installExitHook();
    }

    public Integer getPort() {
        if (props.getPort() != null) return props.getPort();
        return 6379;
    }

    private void installExitHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::destroy, "RedisInstanceCleaner"));
    }

    public RedisServer getRedisServer() {
        return redisServer;
    }
}
