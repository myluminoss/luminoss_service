package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author ruoyi
 * @date 2024-12-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_transaction_log")
public class AiTransactionLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * id
     */
    @NotNull(message = "id", groups = { AddGroup.class, EditGroup.class })
    private Long aiAgentId;

    /**
     *
     */
    private String account;
    /**
     *
     */
    private String avatar;
    /**
     *
     */
    private Long avatarIndex;
    /**
     * Sell,Buy
     */
    private String type;
    /**
     *
     */
    private BigDecimal sol;
    /**
     * token
     */
    private String token;
    /**
     * hash
     */
    private String transaction;
    /**
     *
     */
    private String remark;

}
