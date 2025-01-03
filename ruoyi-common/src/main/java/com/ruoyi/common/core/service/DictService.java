package com.ruoyi.common.core.service;

import java.util.Map;

/**
 *
 *
 * @author Lion Li
 */
public interface DictService {

    /**
     *
     */
    String SEPARATOR = ",";

    /**
     *
     *
     * @param dictType
     * @param dictValue
     * @return
     */
    default String getDictLabel(String dictType, String dictValue) {
        return getDictLabel(dictType, dictValue, SEPARATOR);
    }

    /**
     *
     *
     * @param dictType
     * @param dictLabel
     * @return
     */
    default String getDictValue(String dictType, String dictLabel) {
        return getDictValue(dictType, dictLabel, SEPARATOR);
    }

    /**
     *
     *
     * @param dictType
     * @param dictValue
     * @param separator
     * @return
     */
    String getDictLabel(String dictType, String dictValue, String separator);

    /**
     *
     *
     * @param dictType
     * @param dictLabel
     * @param separator
     * @return
     */
    String getDictValue(String dictType, String dictLabel, String separator);

    /**
     *
     *
     * @param dictType
     * @return dictValuekey,dictLabelMap
     */
    Map<String, String> getAllDictByDictType(String dictType);
}
