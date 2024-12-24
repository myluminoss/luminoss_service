package com.ruoyi.system.domain;

import lombok.Data;

/**
 *
 * @author Lion Li
 */

@Data
public class SysUserOnline {

    /**
     *
     */
    private String tokenId;

    /**
     *
     */
    private String deptName;

    /**
     *
     */
    private String userName;

    /**
     * IP
     */
    private String ipaddr;

    /**
     *
     */
    private String loginLocation;

    /**
     *
     */
    private String browser;

    /**
     *
     */
    private String os;

    /**
     *
     */
    private Long loginTime;

}
