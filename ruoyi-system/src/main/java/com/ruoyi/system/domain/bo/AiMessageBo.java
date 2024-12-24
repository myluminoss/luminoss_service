package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Ai ai_message
 *
 * @author ruoyi
 * @date 2024-12-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AiMessageBo extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    @NotNull(message = "id", groups = { AddGroup.class, EditGroup.class })
    private Long aiAgentId;

    /**
     * id
     */
    private Long userId;

    /**
     * id
     */
    private Long sessionId;

    /**
     * data
     */
    @NotBlank(message = "data", groups = { AddGroup.class, EditGroup.class })
    private String data;

    /**
     *
     */
    private String remark;


}
