package com.ruoyi.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.annotation.Sensitive;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.SensitiveStrategy;
import com.ruoyi.common.xss.Xss;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 *  sys_user
 *
 * @author Lion Li
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseEntity {

    /**
     * ID
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * ID
     */
    private Long deptId;

    /**
     *
     */
    @Xss(message = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 30, message = "{max}")
    private String userName;

    /**
     *
     */
    @Xss(message = "")
    @NotBlank(message = "")
    @Size(min = 0, max = 30, message = "{max}")
    private String nickName;

    /**
     * （sys_user）
     */
    private String userType;

    /**
     *
     */
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    @Email(message = "")
    @Size(min = 0, max = 50, message = "{max}")
    private String email;

    /**
     *
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String phonenumber;

    /**
     *
     */
    private String sex;

    /**
     *
     */
    private String avatar;

    private Integer avatarIndex;

    /**
     *
     */
    @TableField(
        insertStrategy = FieldStrategy.NOT_EMPTY,
        updateStrategy = FieldStrategy.NOT_EMPTY,
        whereStrategy = FieldStrategy.NOT_EMPTY
    )
    private String password;

    @JsonIgnore
    @JsonProperty
    public String getPassword() {
        return password;
    }

    /**
     * （0 1）
     */
    private String status;

    /**
     * （0 2）
     */
    @TableLogic
    private String delFlag;

    /**
     * IP
     */
    private String loginIp;

    /**
     *
     */
    private Date loginDate;

    /**
     *
     */
    private String remark;

    /**
     *
     */
    @TableField(exist = false)
    private SysDept dept;

    /**
     *
     */
    @TableField(exist = false)
    private List<SysRole> roles;

    /**
     *
     */
    @TableField(exist = false)
    private Long[] roleIds;

    /**
     *
     */
    @TableField(exist = false)
    private Long[] postIds;

    /**
     *  ID
     */
    @TableField(exist = false)
    private Long roleId;

    public SysUser(Long userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.userId);
    }

}
