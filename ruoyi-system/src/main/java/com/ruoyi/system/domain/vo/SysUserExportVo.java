package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * VO
 *
 * @author Lion Li
 */

@Data
@NoArgsConstructor
public class SysUserExportVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "")
    private Long userId;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String userName;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String nickName;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String email;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String phonenumber;

    /**
     *
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String sex;

    /**
     * （0 1）
     */
    @ExcelProperty(value = "", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * IP
     */
    @ExcelProperty(value = "IP")
    private String loginIp;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Date loginDate;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String deptName;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String leader;

}
