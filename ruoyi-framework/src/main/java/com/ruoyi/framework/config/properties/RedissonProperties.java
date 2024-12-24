package com.ruoyi.framework.config.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Redisson
 *
 * @author Lion Li
 */
@Data
@Component
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {

    /**
     * rediskey
     */
    private String keyPrefix;

    /**
     * , =  * 2
     */
    private int threads;

    /**
     * Netty, =  * 2
     */
    private int nettyThreads;

    /**
     *
     */
    private SingleServerConfig singleServerConfig;

    /**
     *
     */
    private ClusterServersConfig clusterServersConfig;

    @Data
    @NoArgsConstructor
    public static class SingleServerConfig {

        /**
         *
         */
        private String clientName;

        /**
         *
         */
        private int connectionMinimumIdleSize;

        /**
         *
         */
        private int connectionPoolSize;

        /**
         * ，：
         */
        private int idleConnectionTimeout;

        /**
         * ，：
         */
        private int timeout;

        /**
         *
         */
        private int subscriptionConnectionPoolSize;

    }

    @Data
    @NoArgsConstructor
    public static class ClusterServersConfig {

        /**
         *
         */
        private String clientName;

        /**
         * master
         */
        private int masterConnectionMinimumIdleSize;

        /**
         * master
         */
        private int masterConnectionPoolSize;

        /**
         * slave
         */
        private int slaveConnectionMinimumIdleSize;

        /**
         * slave
         */
        private int slaveConnectionPoolSize;

        /**
         * ，：
         */
        private int idleConnectionTimeout;

        /**
         * ，：
         */
        private int timeout;

        /**
         *
         */
        private int subscriptionConnectionPoolSize;

        /**
         *
         */
        private ReadMode readMode;

        /**
         *
         */
        private SubscriptionMode subscriptionMode;

    }

}
