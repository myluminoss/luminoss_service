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
     * user id
     */
    @ExcelProperty(value = "user id")
    private Long userId;

    /**
     * times
     */
    @ExcelProperty(value = "times")
    private Long times;

    /**
     * name
     */
    @ExcelProperty(value = "name")
    private String name;

    /**
     * check in integral
     */
    @ExcelProperty(value = "integral")
    private Integer integral;

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
     * meg
     */
    @ExcelProperty(value = "msg")
    private String msg;

    /**
     * remark
     */
    @ExcelProperty(value = "remark")
    private String remark;

    /**
     * create time
     */
    @ExcelProperty(value = "create time")
    private Date createTime;

}
