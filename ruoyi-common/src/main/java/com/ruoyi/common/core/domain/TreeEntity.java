package com.ruoyi.common.core.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TreeEntity<T> extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * ID
     */
    private Long parentId;

    /**
     *
     */
    @TableField(exist = false)
    private List<T> children = new ArrayList<>();

}
