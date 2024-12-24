package com.ruoyi.common.constant;

/**
 *
 *
 * @author ruoyi
 */
public interface GenConstants {
    /**
     * （）
     */
    String TPL_CRUD = "crud";

    /**
     * （）
     */
    String TPL_TREE = "tree";

    /**
     * （）
     */
    String TPL_SUB = "sub";

    /**
     *
     */
    String TREE_CODE = "treeCode";

    /**
     *
     */
    String TREE_PARENT_CODE = "treeParentCode";

    /**
     *
     */
    String TREE_NAME = "treeName";

    /**
     * ID
     */
    String PARENT_MENU_ID = "parentMenuId";

    /**
     *
     */
    String PARENT_MENU_NAME = "parentMenuName";

    /**
     *
     */
    String[] COLUMNTYPE_STR = {"char", "varchar", "nvarchar", "varchar2"};

    /**
     *
     */
    String[] COLUMNTYPE_TEXT = {"tinytext", "text", "mediumtext", "longtext"};

    /**
     *
     */
    String[] COLUMNTYPE_TIME = {"datetime", "time", "date", "timestamp"};

    /**
     *
     */
    String[] COLUMNTYPE_NUMBER = {"tinyint", "smallint", "mediumint", "int", "number", "integer",
        "bit", "bigint", "float", "double", "decimal"};

    /**
     * BO
     */
    String[] COLUMNNAME_NOT_ADD = {"create_by", "create_time", "del_flag", "update_by",
        "update_time", "version"};

    /**
     * BO
     */
    String[] COLUMNNAME_NOT_EDIT = {"create_by", "create_time", "del_flag", "update_by",
        "update_time", "version"};

    /**
     * VO
     */
    String[] COLUMNNAME_NOT_LIST = {"create_by", "create_time", "del_flag", "update_by",
        "update_time", "version"};

    /**
     * BO
     */
    String[] COLUMNNAME_NOT_QUERY = {"id", "create_by", "create_time", "del_flag", "update_by",
        "update_time", "remark", "version"};

    /**
     * Entity
     */
    String[] BASE_ENTITY = {"createBy", "createTime", "updateBy", "updateTime"};

    /**
     * Tree
     */
    String[] TREE_ENTITY = {"parentName", "parentId", "children"};

    /**
     *
     */
    String HTML_INPUT = "input";

    /**
     *
     */
    String HTML_TEXTAREA = "textarea";

    /**
     *
     */
    String HTML_SELECT = "select";

    /**
     *
     */
    String HTML_RADIO = "radio";

    /**
     *
     */
    String HTML_CHECKBOX = "checkbox";

    /**
     *
     */
    String HTML_DATETIME = "datetime";

    /**
     *
     */
    String HTML_IMAGE_UPLOAD = "imageUpload";

    /**
     *
     */
    String HTML_FILE_UPLOAD = "fileUpload";

    /**
     *
     */
    String HTML_EDITOR = "editor";

    /**
     *
     */
    String TYPE_STRING = "String";

    /**
     *
     */
    String TYPE_INTEGER = "Integer";

    /**
     *
     */
    String TYPE_LONG = "Long";

    /**
     *
     */
    String TYPE_DOUBLE = "Double";

    /**
     *
     */
    String TYPE_BIGDECIMAL = "BigDecimal";

    /**
     *
     */
    String TYPE_DATE = "Date";

    /**
     *
     */
    String QUERY_LIKE = "LIKE";

    /**
     *
     */
    String QUERY_EQ = "EQ";

    /**
     *
     */
    String REQUIRE = "1";
}
