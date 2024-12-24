package com.ruoyi.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.core.domain.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *  sys_menu
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class SysMenu extends TreeEntity<SysMenu> {

    /**
     * ID
     */
    @TableId(value = "menu_id")
    private Long menuId;

    /**
     *
     */
    @NotBlank(message = "")
    @Size(min = 0, max = 50, message = "{max}")
    private String menuName;

    /**
     *
     */
    @NotNull(message = "")
    private Integer orderNum;

    /**
     *
     */
    @Size(min = 0, max = 200, message = "{max}")
    private String path;

    /**
     *
     */
    @Size(min = 0, max = 200, message = "{max}")
    private String component;

    /**
     *
     */
    private String queryParam;

    /**
     * （0 1）
     */
    private String isFrame;

    /**
     * （0 1）
     */
    private String isCache;

    /**
     * （M C F）
     */
    @NotBlank(message = "")
    private String menuType;

    /**
     * （0 1）
     */
    private String visible;

    /**
     * （0 1）
     */
    private String status;

    /**
     *
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Size(min = 0, max = 100, message = "{max}")
    private String perms;

    /**
     *
     */
    private String icon;

    /**
     *
     */
    private String remark;

}
