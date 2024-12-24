package com.ruoyi.system.domain;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * sys_post
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_post")
@ExcelIgnoreUnannotated
public class SysPost extends BaseEntity {

    /**
     */
    @ExcelProperty(value = "")
    @TableId(value = "post_id")
    private Long postId;

    /**
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 64, message = "{max}")
    private String postCode;

    /**
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 50, message = "{max}")
    private String postName;

    /**
     */
    @ExcelProperty(value = "")
    @NotNull(message = "")
    private Integer postSort;

    /**
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     */
    private String remark;

    /**
     */
    @TableField(exist = false)
    private boolean flag = false;

}
