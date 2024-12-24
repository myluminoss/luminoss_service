package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

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


}
