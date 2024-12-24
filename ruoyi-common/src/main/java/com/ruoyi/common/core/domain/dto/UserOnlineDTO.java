package com.ruoyi.common.core.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 *
 * @author ruoyi
 */

@Data
@NoArgsConstructor
public class UserOnlineDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
