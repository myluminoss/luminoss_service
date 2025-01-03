package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 *
 *
 * @author ruoyi
 * @date 2024-12-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserIntegralLogBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id", groups = { EditGroup.class })
    private Long id;

    private Integer type;

    private String flag;

    private Long change;

    /**
     * id
     */
    @NotNull(message = "id", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     *
     */
    @NotNull(message = "", groups = { AddGroup.class, EditGroup.class })
    private Long integral;

    /**
     *
     */
    private String remark;


}
