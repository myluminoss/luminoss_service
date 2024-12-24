package com.ruoyi.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Lion Li
 */
@Data
@Component
@ConfigurationProperties(prefix = "thread-pool")
public class ThreadPoolProperties {

    /**
     *
     */
    private boolean enabled;

    /**
     *
     */
    private int queueCapacity;

    /**
     *
     */
    private int keepAliveSeconds;

}
