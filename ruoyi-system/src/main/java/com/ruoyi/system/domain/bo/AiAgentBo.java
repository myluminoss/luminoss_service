package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * Ai ai_agent
 *
 * @author ruoyi
 * @date 2024-12-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AiAgentBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id", groups = { EditGroup.class })
    private Long id;

    /**
     * id
     */
    private Long userId;

    /**
     *
     */
    private String status;

    /**
     *
     */
    @NotBlank(message = "", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * ticker
     */
    @NotBlank(message = "ticker", groups = { AddGroup.class, EditGroup.class })
    private String ticker;

    /**
     *
     */
    private String iconImg;

    /**
     *
     */
    @NotBlank(message = "", groups = { AddGroup.class, EditGroup.class })
    private String img;

    /**
     *
     */
    @NotBlank(message = "", groups = { AddGroup.class, EditGroup.class })
    private String desc;

    /**
     *
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
