package com.ruoyi.generator.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ruoyi.common.constant.GenConstants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ArrayUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 *  gen_table
 *
 * @author Lion Li
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("gen_table")
public class GenTable extends BaseEntity {

    /**
     *
     */
    @TableId(value = "table_id")
    private Long tableId;

    /**
     *
     */
    @NotBlank(message = "")
    private String tableName;

    /**
     *
     */
    @NotBlank(message = "")
    private String tableComment;

    /**
     *
     */
    private String subTableName;

    /**
     *
     */
    private String subTableFkName;

    /**
     * ()
     */
    @NotBlank(message = "")
    private String className;

    /**
     * （crud tree sub）
     */
    private String tplCategory;

    /**
     *
     */
    @NotBlank(message = "")
    private String packageName;

    /**
     *
     */
    @NotBlank(message = "")
    private String moduleName;

    /**
     *
     */
    @NotBlank(message = "")
    private String businessName;

    /**
     *
     */
    @NotBlank(message = "")
    private String functionName;

    /**
     *
     */
    @NotBlank(message = "")
    private String functionAuthor;

    /**
     * （0zip 1）
     */
    private String genType;

    /**
     * （）
     */
    @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String genPath;

    /**
     *
     */
    @TableField(exist = false)
    private GenTableColumn pkColumn;

    /**
     *
     */
    @TableField(exist = false)
    private GenTable subTable;

    /**
     *
     */
    @Valid
    @TableField(exist = false)
    private List<GenTableColumn> columns;

    /**
     *
     */
    private String options;

    /**
     *
     */
    private String remark;

    /**
     *
     */
    @TableField(exist = false)
    private String treeCode;

    /**
     *
     */
    @TableField(exist = false)
    private String treeParentCode;

    /**
     *
     */
    @TableField(exist = false)
    private String treeName;

    /*
     * id
     */
    @TableField(exist = false)
    private List<Long> menuIds;

    /**
     * ID
     */
    @TableField(exist = false)
    private String parentMenuId;

    /**
     *
     */
    @TableField(exist = false)
    private String parentMenuName;

    public boolean isSub() {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_SUB, tplCategory);
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }
}
