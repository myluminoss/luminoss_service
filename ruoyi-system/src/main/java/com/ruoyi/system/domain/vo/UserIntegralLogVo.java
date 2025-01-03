package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * user integral log user_integral_log
 *
 * @author ruoyi
 * @date 2024-12-25
 */
@Data
@ExcelIgnoreUnannotated
public class UserIntegralLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    private Integer type;

    private String flag;

    private Long change;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long userId;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long integral;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;


}
