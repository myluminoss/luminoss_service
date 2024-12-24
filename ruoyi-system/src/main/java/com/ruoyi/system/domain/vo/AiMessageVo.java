package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;

import java.io.Serializable;

/**
 * Ai ai_message
 *
 * @author ruoyi
 * @date 2024-12-23
 */
@Data
@ExcelIgnoreUnannotated
public class AiMessageVo implements Serializable {

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
    private Long aiAgentId;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long userId;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long sessionId;

    /**
     * data
     */
    @ExcelProperty(value = "data")
    private String data;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
