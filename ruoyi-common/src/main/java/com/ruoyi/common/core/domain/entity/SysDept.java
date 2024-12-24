package com.ruoyi.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *  sys_dept
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
public class SysDept extends TreeEntity<SysDept> {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "dept_id")
    private Long deptId;

    /**
     *
     */
    @NotBlank(message = "")
    @Size(min = 0, max = 30, message = "{max}")
    private String deptName;

    /**
     *
     */
    @NotNull(message = "")
    private Integer orderNum;

    /**
     *
     */
    private String leader;

    /**
     *
     */
    @Size(min = 0, max = 11, message = "{max}")
    private String phone;

    /**
     *
     */
    @Email(message = "")
    @Size(min = 0, max = 50, message = "{max}")
    private String email;

    /**
     * :0,1
     */
    private String status;

    /**
     * （0 2）
     */
    @TableLogic
    private String delFlag;

    /**
     *
     */
    private String ancestors;

}
