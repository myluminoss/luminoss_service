package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OSS sys_oss
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOssBo extends BaseEntity {

    /**
     * ossId
     */
    private Long ossId;

    /**
     *
     */
    private String fileName;

    /**
     *
     */
    private String originalName;

    /**
     *
     */
    private String fileSuffix;

    /**
     * URL
     */
    private String url;

    /**
     *
     */
    private String service;

}
