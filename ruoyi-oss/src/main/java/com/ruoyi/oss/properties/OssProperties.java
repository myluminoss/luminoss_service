package com.ruoyi.oss.properties;

import lombok.Data;

/**
 * OSS
 *
 * @author Lion Li
 */
@Data
public class OssProperties {

    /**
     *
     */
    private String endpoint;

    /**
     *
     */
    private String domain;

    /**
     *
     */
    private String prefix;

    /**
     * ACCESS_KEY
     */
    private String accessKey;

    /**
     * SECRET_KEY
     */
    private String secretKey;

    /**
     */
    private String bucketName;

    /**
     */
    private String region;

    /**
     */
    private String isHttps;

    /**
     */
    private String accessPolicy;

}
