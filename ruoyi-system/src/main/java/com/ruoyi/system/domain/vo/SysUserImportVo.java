package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * VO
 *
 * @author Lion Li
 */

@Data
@NoArgsConstructor
// @Accessors(chain = true) //  set
public class SysUserImportVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "")
    private Long userId;

    /**
     * ID
     */
    @ExcelProperty(value = "")
    private Long deptId;

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

}
