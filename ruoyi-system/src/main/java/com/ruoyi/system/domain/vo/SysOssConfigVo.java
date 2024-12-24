package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;


/**
 *  sys_oss_config
 *
 * @author Lion Li
 * @author
 * @date 2021-08-13
 */
@Data
@ExcelIgnoreUnannotated
public class SysOssConfigVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long ossConfigId;

    /**
     * key
     */
    private String configKey;

    /**
     * accessKey
     */
    private String accessKey;

    /**
     *
     */
    private String secretKey;

    /**
     *
     */
    private String bucketName;

    /**
     *
     */
    private String prefix;

    /**
     *
     */
    private String endpoint;

    /**
     *
     */
    private String domain;

    /**
     * https（Y=,N=）
     */
    private String isHttps;

    /**
     *
     */
    private String region;

    /**
     * （0=,1=）
     */
    private String status;

    /**
     *
     */
    private String ext1;

    /**
     *
     */
    private String remark;

    /**
     * (0private 1public 2custom)
     */
    private String accessPolicy;

}
