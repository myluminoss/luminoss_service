package com.ruoyi.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.domain.vo.SysUserSimpleVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

/**
 * Ai ai_agent
 *
 * @author ruoyi
 * @date 2024-12-22
 */
@Data
@ExcelIgnoreUnannotated
public class AiAgentVo implements Serializable {

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

    private SysUserSimpleVo user;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String status;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String title;

    /**
     * ticker
     */
    @ExcelProperty(value = "ticker")
    private String ticker;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String iconImg;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String img;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String desc;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;

    /**
     *
     */
    private String hash;

    private String tags;

    private BigDecimal marketCap;

    private BigDecimal progress;

    private BigDecimal dayChange;

    private Long totalTransactions;

    private Long invokeApi;

    private Integer decimals;
}
