package com.ruoyi.common.core.domain.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *  sys_dict_type
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_type")
@ExcelIgnoreUnannotated
public class SysDictType extends BaseEntity {

    /**
     *
     */
    @ExcelProperty(value = "")
    @TableId(value = "dict_id")
    private Long dictId;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 100, message = "{max}")
    private String dictName;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 100, message = "{max}")
    @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "，（，，）")
    private String dictType;

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

}
