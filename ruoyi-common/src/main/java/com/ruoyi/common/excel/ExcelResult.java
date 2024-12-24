package com.ruoyi.common.excel;

import java.util.List;

/**
 *
 * @author Lion Li
 */
public interface ExcelResult<T> {

    /**
     */
    List<T> getList();

    /**
     */
    List<String> getErrorList();

    /**
     */
    String getAnalysis();
}
