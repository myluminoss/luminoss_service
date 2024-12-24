package com.ruoyi.common.core.domain.event;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 *
 *
 * @author Lion Li
 */

@Data
public class LogininforEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String username;

    /**
     *  0 1
     */
    private String status;

    /**
     *
     */
    private String message;

    /**
     *
     */
    private HttpServletRequest request;

    /**
     *
     */
    private Object[] args;

}
