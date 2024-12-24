package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  user_token
 *
 * @author ruoyi
 * @date 2024-12-05
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserTokenBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id", groups = { EditGroup.class })
    private Long id;

    /**
     * id
     */
    @NotNull(message = "id", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     *  eth
     */
    @NotBlank(message = " eth", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     *
     */
    @NotBlank(message = "", groups = { AddGroup.class, EditGroup.class })
    private String token;

    /**
     *
     */
    private String remark;


}
