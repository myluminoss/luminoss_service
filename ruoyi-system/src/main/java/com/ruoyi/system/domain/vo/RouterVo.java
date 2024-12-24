package com.ruoyi.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo {

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String path;

    /**
     * ， true
     */
    private boolean hidden;

    /**
     * ， noRedirect
     */
    private String redirect;

    /**
     *
     */
    private String component;

    /**
     * ： {"id": 1, "name": "ry"}
     */
    private String query;

    /**
     *  children 1，--
     */
    private Boolean alwaysShow;

    /**
     *
     */
    private MetaVo meta;

    /**
     *
     */
    private List<RouterVo> children;

}
