package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sys_oss_config
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_oss_config")
public class SysOssConfig extends BaseEntity {

    /**
     */
    @TableId(value = "oss_config_id")
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
     * https（0 1）
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
