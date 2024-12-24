package com.ruoyi.common.core.domain.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *  sys_dict_data
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_data")
@ExcelIgnoreUnannotated
public class SysDictData extends BaseEntity {

    /**
     *
     */
    @ExcelProperty(value = "")
    @TableId(value = "dict_code")
    private Long dictCode;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Integer dictSort;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 100, message = "{max}")
    private String dictLabel;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 100, message = "{max}")
    private String dictValue;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 100, message = "{max}")
    private String dictType;

    /**
     * （）
     */
    @Size(min = 0, max = 100, message = "{max}")
    private String cssClass;

    /**
     *
     */
    private String listClass;

    /**
     * （Y N）
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String isDefault;

    /**
     * （0 1）
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     *
     */
    private String remark;

    public boolean getDefault() {
        return UserConstants.YES.equals(this.isDefault);
    }

}
