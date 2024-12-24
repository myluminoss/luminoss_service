package com.ruoyi.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * xss
 *
 * @author Lion Li
 */
@Data
@Component
@ConfigurationProperties(prefix = "xss")
public class XssProperties {

    /**
     *
     */
    private String enabled;

    /**
     * （）
     */
    private String excludes;

    /**
     *
     */
    private String urlPatterns;

}
