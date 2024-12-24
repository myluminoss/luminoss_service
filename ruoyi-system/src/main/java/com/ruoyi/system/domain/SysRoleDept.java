package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * sys_role_dept
 *
 * @author Lion Li
 */

@Data
@TableName("sys_role_dept")
public class SysRoleDept {

    /**
     */
    @TableId(type = IdType.INPUT)
    private Long roleId;

    /**
     */
    private Long deptId;

}
