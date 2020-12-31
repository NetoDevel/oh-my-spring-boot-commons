package com.netodevel.eb.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import redis.embedded.RedisServer;

import java.io.IOException;

public class EmbeddedRedis implements InitializingBean {

    private Log log = LogFactory.getLog(EmbeddedRedis.class);

    private RedisServer redisServer;

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
            this.redisServer = new RedisServer(6379);
        } catch (IOException e) {
            log.error("Error when starting redis, error = " + e.getMessage());
        }
        redisServer.start();
        log.info("Redis started. Listening on tcp://127.0.0.1:6379");

        installExitHook();
    }

    private void installExitHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::destroy, "RedisInstanceCleaner"));
    }
}
