package com.ruoyi.generator.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author Lion Li
 */
public interface IGenTableService {

    /**
     *
     *
     * @param tableId
     * @return
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

    /**
     *
     *
     * @param genTable
     * @return
     */
    TableDataInfo<GenTable> selectPageGenTableList(GenTable genTable, PageQuery pageQuery);

    /**
     *
     *
     * @param genTable
     * @return
     */
    TableDataInfo<GenTable> selectPageDbTableList(GenTable genTable, PageQuery pageQuery);

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
     *
     *
     * @param id ID
     * @return
     */
    GenTable selectGenTableById(Long id);

    /**
     *
     *
     * @param genTable
     * @return
     */
    void updateGenTable(GenTable genTable);

    /**
     *
     *
     * @param tableIds ID
     * @return
     */
    void deleteGenTableByIds(Long[] tableIds);

    /**
     *
     *
     * @param tableList
     */
    void importGenTable(List<GenTable> tableList);

    /**
     *
     *
     * @param tableId
     * @return
     */
    Map<String, String> previewCode(Long tableId);

    /**
     * （）
     *
     * @param tableName
     * @return
     */
    byte[] downloadCode(String tableName);

    /**
     * （）
     *
     * @param tableName
     * @return
     */
    void generatorCode(String tableName);

    /**
     *
     *
     * @param tableName
     */
    void synchDb(String tableName);

    /**
     * （）
     *
     * @param tableNames
     * @return
     */
    byte[] downloadCode(String[] tableNames);

    /**
     *
     *
     * @param genTable
     */
    void validateEdit(GenTable genTable);
}
