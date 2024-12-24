package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 *  check_in_log
 *
 * @author ruoyi
 * @date 2024-12-04
 */
@Data
@ExcelIgnoreUnannotated
public class CheckInLogVo implements Serializable {

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
    private Long times;

    /**
     * hash
     */
    @ExcelProperty(value = "hash")
    private String hash;

    /**
     *  wait success fail
     */
    @ExcelProperty(value = " wait success fail")
    private String status;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String msg;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;

    @ExcelProperty(value = "")
    private Date createTime;

}
