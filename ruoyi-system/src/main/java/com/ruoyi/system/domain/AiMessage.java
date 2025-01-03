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
 * @date 2024-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_message")
public class AiMessage extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     */
    private Long aiAgentId;

    /**
     */
    private Long userId;

    /**
     */
    private Long sessionId;

    /**
     * data
     */
    private String data;

    /**
     */
    private String remark;

    private String role;
}
