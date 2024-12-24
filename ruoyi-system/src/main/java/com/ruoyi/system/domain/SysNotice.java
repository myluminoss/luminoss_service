package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.xss.Xss;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_notice")
public class SysNotice extends BaseEntity {

    /**
     */
    @TableId(value = "notice_id")
    private Long noticeId;

    /**
     */
    @Xss(message = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 50, message = "{max}")
    private String noticeTitle;

    /**
     * （1 2）
     */
    private String noticeType;

    /**
     *
     */
    private String noticeContent;

    /**
     * （0 1）
     */
    private String status;

    /**
     *
     */
    private String remark;

}
