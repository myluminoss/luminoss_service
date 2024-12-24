package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_oss")
public class SysOss extends BaseEntity {

    /**
     */
    @TableId(value = "oss_id")
    private Long ossId;

    /**
     */
    private String fileName;

    /**
     */
    private String originalName;

    /**
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
