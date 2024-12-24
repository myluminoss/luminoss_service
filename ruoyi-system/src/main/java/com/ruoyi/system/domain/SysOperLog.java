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
 *
 * @author Lion Li
 */

@Data
@TableName("sys_oper_log")
@ExcelIgnoreUnannotated
public class SysOperLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     */
    @ExcelProperty(value = "")
    @TableId(value = "oper_id")
    private Long operId;

    /**
     */
    @ExcelProperty(value = "")
    private String title;

    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_oper_type")
    private Integer businessType;

    /**
     *
     */
    @TableField(exist = false)
    private Integer[] businessTypes;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String method;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String requestMethod;

    /**
     * （0 1 2）
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=,1=,2=")
    private Integer operatorType;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String operName;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String deptName;

    /**
     * url
     */
    @ExcelProperty(value = "")
    private String operUrl;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String operIp;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String operLocation;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String operParam;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String jsonResult;

    /**
     * （0 1）
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_common_status")
    private Integer status;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String errorMsg;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Date operTime;

    /**
     *
     */
    @TableField(exist = false)
    private Map<String, Object> params = new HashMap<>();

}
