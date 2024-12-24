package com.ruoyi.common.excel;

import com.alibaba.excel.read.listener.ReadListener;

/**
 *
 * @author Lion Li
 */
public interface ExcelListener<T> extends ReadListener<T> {

    ExcelResult<T> getExcelResult();

}
