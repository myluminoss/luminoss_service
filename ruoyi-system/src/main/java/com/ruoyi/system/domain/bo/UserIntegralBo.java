package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 *
 *
 * @author ruoyi
 * @date 2024-12-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserIntegralBo extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long userId;

    /**
     *
     */
    private Long integral;

    /**
     *
     */
    private String remark;


}
