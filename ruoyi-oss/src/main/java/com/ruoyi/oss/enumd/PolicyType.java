package com.ruoyi.oss.enumd;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * minio
 *
 * @author Lion Li
 */
@Getter
@AllArgsConstructor
public enum PolicyType {

    /**
     *
     */
    READ("read-only"),

    /**
     *
     */
    WRITE("write-only"),

    /**
     *
     */
    READ_WRITE("read-write");

    /**
     *
     */
    private final String type;

}
