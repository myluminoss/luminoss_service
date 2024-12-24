package com.ruoyi.system.domain;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * sys_logininfor
 *
 * @author Lion Li
 */

@Data
@TableName("sys_logininfor")
@ExcelIgnoreUnannotated
public class SysLogininfor implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "")
    @TableId(value = "info_id")
    private Long infoId;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String userName;

    /**
     *  0 1
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_common_status")
    private String status;

    /**
     * IP
     */
    @ExcelProperty(value = "")
    private String ipaddr;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String loginLocation;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String browser;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String os;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String msg;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Date loginTime;

    /**
     *
     */
    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();

}
