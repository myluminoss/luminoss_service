package com.ruoyi.system.domain.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  check_in_log
 *
 * @author ruoyi
 * @date 2024-12-04
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CheckInLogBo extends BaseEntity {

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
    @NotNull(message = "", groups = { AddGroup.class, EditGroup.class })
    private Long times;

    /**
     * check in integral
     */
    private Integer integral;

    /**
     * hash
     */
    @NotBlank(message = "hash", groups = { AddGroup.class, EditGroup.class })
    private String hash;

    /**
     *  wait success fail
     */
    private String status;

    /**
     *
     */
    private String msg;

    /**
     *
     */
    private String remark;


}
