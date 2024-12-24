package com.ruoyi.framework.config.properties;

import com.ruoyi.common.enums.AlgorithmType;
import com.ruoyi.common.enums.EncodeType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author
 * @version 4.6.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "mybatis-encryptor")
public class EncryptorProperties {

    /**
     *
     */
    private Boolean enable;

    /**
     *
     */
    private AlgorithmType algorithm;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String publicKey;

    /**
     *
     */
    private String privateKey;

    /**
     * ，base64/hex
     */
    private EncodeType encode;

}
