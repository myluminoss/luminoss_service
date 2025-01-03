package com.ruoyi.common.encrypt;

import com.ruoyi.common.enums.AlgorithmType;
import com.ruoyi.common.enums.EncodeType;
import lombok.Data;

/**
 *  encryptor.
 *
 * @author
 * @version 4.6.0
 */
@Data
public class EncryptContext {

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
     * ,base64/hex
     */
    private EncodeType encode;

}
