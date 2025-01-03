package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * user integral user_integral
 *
 * @author ruoyi
 * @date 2024-12-25
 */
@Data
@ExcelIgnoreUnannotated
public class UserIntegralVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

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
