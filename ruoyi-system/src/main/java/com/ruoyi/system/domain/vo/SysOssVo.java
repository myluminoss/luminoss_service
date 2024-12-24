package com.ruoyi.system.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * OSS sys_oss
 *
 * @author Lion Li
 */
@Data
public class SysOssVo {

    private static final long serialVersionUID = 1L;

    /**
     *
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
    private Date createTime;

    /**
     *
     */
    private String createBy;

    /**
     *
     */
    private String service;


}
