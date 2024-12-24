package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysDictType;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysDictTypeService {


    TableDataInfo<SysDictType> selectPageDictTypeList(SysDictType dictType, PageQuery pageQuery);

    /**
     *
     *
     * @param dictType
     * @return
     */
    List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     *
     *
     * @return
     */
    List<SysDictType> selectDictTypeAll();

    /**
     *
     *
     * @param dictType
     * @return
     */
    List<SysDictData> selectDictDataByType(String dictType);

    /**
     * ID
     *
     * @param dictId ID
     * @return
     */
    SysDictType selectDictTypeById(Long dictId);

    /**
     *
     *
     * @param dictType
     * @return
     */
    SysDictType selectDictTypeByType(String dictType);

    /**
     *
     *
     * @param dictIds ID
     */
    void deleteDictTypeByIds(Long[] dictIds);

    /**
     *
     */
    void loadingDictCache();

    /**
     *
     */
    void clearDictCache();

    /**
     *
     */
    void resetDictCache();

    /**
     *
     *
     * @param dictType
     * @return
     */
    List<SysDictData> insertDictType(SysDictType dictType);

    /**
     *
     *
     * @param dictType
     * @return
     */
    List<SysDictData> updateDictType(SysDictType dictType);

    /**
     *
     *
     * @param dictType
     * @return
     */
    boolean checkDictTypeUnique(SysDictType dictType);
}
