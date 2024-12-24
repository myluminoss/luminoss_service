package com.ruoyi.common.core.domain.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *  sys_role
 *
 * @author Lion Li
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@ExcelIgnoreUnannotated
public class SysRole extends BaseEntity {

    /**
     * ID
     */
    @ExcelProperty(value = "")
    @TableId(value = "role_id")
    private Long roleId;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 30, message = "{max}")
    private String roleName;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 100, message = "{max}")
    private String roleKey;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotNull(message = "")
    private Integer roleSort;

    /**
     * （1：；2：；3：；4：；5：）
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "1=,2=,3=,4=,5=")
    private String dataScope;

    /**
     * （ 0： 1：）
     */
    private Boolean menuCheckStrictly;

    /**
     * （0： 1： ）
     */
    private Boolean deptCheckStrictly;

    /**
     * （0 1）
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * （0 2）
     */
    @TableLogic
    private String delFlag;

    /**
     *
     */
    private String remark;

    /**
     *
     */
    @TableField(exist = false)
    private boolean flag = false;

    /**
     *
     */
    @TableField(exist = false)
    private Long[] menuIds;

    /**
     * （）
     */
    @TableField(exist = false)
    private Long[] deptIds;

    public SysRole(Long roleId) {
        this.roleId = roleId;
    }

    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.roleId);
    }
}
