package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_token")
public class UserToken extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     */
    private Long userId;

    /**
     */
    private String type;

    /**
     */
    private String token;

    /**
     */
    private String remark;

}
