package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *
 *
 * @author ruoyi
 * @date 2024-12-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AiTransactionLogBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id", groups = { EditGroup.class })
    private Long id;

    /**
     * id
     */
    @NotNull(message = "id", groups = { AddGroup.class, EditGroup.class })
    private Long aiAgentId;

    /**
     *
     */
    @NotBlank(message = "", groups = { AddGroup.class, EditGroup.class })
    private String account;

    /**
     *
     */
    @NotBlank(message = "", groups = { AddGroup.class, EditGroup.class })
    private String avatar;

    /**
     *
     */
    @NotNull(message = "", groups = { AddGroup.class, EditGroup.class })
    private Long avatarIndex;

    /**
     * Sell,Buy
     */
    @NotBlank(message = "Sell,Buy", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     *
     */
    @NotNull(message = "", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal sol;

    /**
     * token
     */
    @NotBlank(message = "token", groups = { AddGroup.class, EditGroup.class })
    private String token;

    /**
     * hash
     */
    @NotBlank(message = "hash", groups = { AddGroup.class, EditGroup.class })
    private String transaction;

    /**
     *
     */
    @NotBlank(message = "", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
