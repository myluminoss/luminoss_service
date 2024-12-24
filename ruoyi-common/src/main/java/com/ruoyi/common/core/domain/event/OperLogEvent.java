package com.ruoyi.common.core.domain.event;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author Lion Li
 */

@Data
public class OperLogEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long operId;

    /**
     *
     */
    private String title;

    /**
     * （0 1 2 3）
     */
    private Integer businessType;

    /**
     *
     */
    private Integer[] businessTypes;

    /**
     *
     */
    private String method;

    /**
     *
     */
    private String requestMethod;

    /**
     * （0 1 2）
     */
    private Integer operatorType;

    /**
     *
     */
    private String operName;

    /**
     *
     */
    private String deptName;

    /**
     * url
     */
    private String operUrl;

    /**
     *
     */
    private String operIp;

    /**
     *
     */
    private String operLocation;

    /**
     *
     */
    private String operParam;

    /**
     *
     */
    private String jsonResult;

    /**
     * （0 1）
     */
    private Integer status;

    /**
     *
     */
    private String errorMsg;

    /**
     *
     */
    private Date operTime;

}
