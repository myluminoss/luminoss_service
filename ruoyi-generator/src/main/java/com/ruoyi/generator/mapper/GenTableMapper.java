package com.ruoyi.generator.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.generator.domain.GenTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
@InterceptorIgnore(dataPermission = "true")
public interface GenTableMapper extends BaseMapperPlus<GenTableMapper, GenTable, GenTable> {

    /**
     *
     *
     * @param genTable
     * @return
     */
    Page<GenTable> selectPageDbTableList(@Param("page") Page<GenTable> page, @Param("genTable") GenTable genTable);

    /**
     *
     *
     * @param tableNames
     * @return
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     *
     *
     * @return
     */
    List<GenTable> selectGenTableAll();

    /**
     * ID
     *
     * @param id ID
     * @return
     */
    GenTable selectGenTableById(Long id);

    /**
     *
     *
     * @param tableName
     * @return
     */
    GenTable selectGenTableByName(String tableName);

}
