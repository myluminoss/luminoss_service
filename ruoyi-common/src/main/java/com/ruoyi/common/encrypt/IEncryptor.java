package com.ruoyi.common.encrypt;

import com.ruoyi.common.enums.AlgorithmType;
import com.ruoyi.common.enums.EncodeType;

/**
 *
 *
 * @author
 * @version 4.6.0
 */
public interface IEncryptor {

    /**
     *
     */
    AlgorithmType algorithm();

    /**
     *
     *
     * @param value
     * @param encodeType
     * @return
     */
    String encrypt(String value, EncodeType encodeType);

    /**
     *
     *
     * @param value
     * @return
     */
    String decrypt(String value);
}
