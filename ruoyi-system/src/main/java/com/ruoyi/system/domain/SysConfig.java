package com.ruoyi.system.domain;

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
import javax.validation.constraints.Size;

/**
 * sys_config
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_config")
@ExcelIgnoreUnannotated
public class SysConfig extends BaseEntity {

    /**
     *
     */
    @ExcelProperty(value = "Parameter primary key")
    @TableId(value = "config_id")
    private Long configId;

    /**
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 100, message = "{max}")
    private String configName;

    /**
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 100, message = "{max}")
    private String configKey;

    /**
     *
     */
    @ExcelProperty(value = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 500, message = "{max}")
    private String configValue;

    /**
     * （Y N）
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String configType;

    /**
     *
     */
    private String remark;

}
