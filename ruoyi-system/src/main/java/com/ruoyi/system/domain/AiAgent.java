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
 * @author ruoyi
 * @date 2024-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_agent")
public class AiAgent extends BaseEntity {

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
    private String status;

    /**
     */
    private String title;

    /**
     * ticker
     */
    private String ticker;

    /**
     */
    private String iconImg;

    /**
     */
    private String img;

    /**
     */
    @TableField("`desc`")
    private String desc;

    /**
     */
    private String remark;

    /**
     *
     */
    private String hash;

    private String tags;

    private BigDecimal marketCap;

    private BigDecimal progress;

    private BigDecimal dayChange;

    private Long totalTransactions;

    private Long invokeApi;

    private Integer decimals;
}
