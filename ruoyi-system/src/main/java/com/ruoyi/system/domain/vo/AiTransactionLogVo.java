package com.ruoyi.system.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

import java.io.Serializable;

/**
 * AiTransactionLog ai_transaction_log
 *
 * @author ruoyi
 * @date 2024-12-27
 */
@Data
@ExcelIgnoreUnannotated
public class AiTransactionLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * id
     */
    @NotNull(message = "id", groups = { AddGroup.class, EditGroup.class })
    private Long aiAgentId;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String account;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String avatar;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long avatarIndex;

    /**
     * Sell,Buy
     */
    @ExcelProperty(value = "Sell,Buy")
    private String type;

    /**
     *
     */
    @ExcelProperty(value = "")
    private BigDecimal sol;

    /**
     * token
     */
    @ExcelProperty(value = "token")
    private String token;

    /**
     * hash
     */
    @ExcelProperty(value = "hash")
    private String transaction;

    /**
     *
     */
    @ExcelProperty(value = "")
    private String remark;


}
