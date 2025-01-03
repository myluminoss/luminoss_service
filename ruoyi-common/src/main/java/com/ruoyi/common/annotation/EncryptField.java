package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.AlgorithmType;
import com.ruoyi.common.enums.EncodeType;

import java.lang.annotation.*;

/**
 *
 *
 * @author
 */
@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {

    /**
     *
     */
    AlgorithmType algorithm() default AlgorithmType.DEFAULT;

    /**
     * .AES、SM4
     */
    String password() default "";

    /**
     * .RSA、SM2
     */
    String publicKey() default "";

    /**
     * .RSA、SM2
     */
    String privateKey() default "";

    /**
     * .BASE64
     */
    EncodeType encode() default EncodeType.DEFAULT;

}
