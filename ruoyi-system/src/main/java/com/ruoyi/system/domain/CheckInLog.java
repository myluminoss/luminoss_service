package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 *
 *
 * @author ruoyi
 * @date 2024-12-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("check_in_log")
public class CheckInLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     */
    private Long userId;

    /**
     */
    private Long times;

    /**
     */
    private String hash;

    /**
     */
    private String status;

    /**
     */
    private String msg;

    /**
     */
    private String remark;

}
