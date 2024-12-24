package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user_post")
public class SysUserPost {

    /**
     */
    @TableId(type = IdType.INPUT)
    private Long userId;

    /**
     * ID
     */
    private Long postId;

}
