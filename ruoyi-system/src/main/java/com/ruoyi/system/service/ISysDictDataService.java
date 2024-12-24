package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysDictDataService {


    TableDataInfo<SysDictData> selectPageDictDataList(SysDictData dictData, PageQuery pageQuery);

    /**
     *
     *
     * @param dictData
     * @return
     */
    List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     *
     *
     * @param dictType
     * @param dictValue
     * @return
     */
    String selectDictLabel(String dictType, String dictValue);

    /**
     * ID
     *
     * @param dictCode ID
     * @return
     */
    SysDictData selectDictDataById(Long dictCode);

    /**
     *
     *
     * @param dictCodes ID
     */
    void deleteDictDataByIds(Long[] dictCodes);

    /**
     *
     *
     * @param dictData
     * @return
     */
    List<SysDictData> insertDictData(SysDictData dictData);

    /**
     *
     *
     * @param dictData
     * @return
     */
    List<SysDictData> updateDictData(SysDictData dictData);
}
