package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 *  user_token
 *
 * @author ruoyi
 * @date 2024-12-05
 */
@Data
@ExcelIgnoreUnannotated
public class UserTokenVo implements Serializable {

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
     *  eth
     */
    @ExcelProperty(value = " eth")
    private String type;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String token;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;


}
