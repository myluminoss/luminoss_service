package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysOperLog;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysOperLogService {

    TableDataInfo<SysOperLog> selectPageOperLogList(SysOperLog operLog, PageQuery pageQuery);

    /**
     *
     *
     * @param operLog
     */
    void insertOperlog(SysOperLog operLog);

    /**
     *
     *
     * @param operLog
     * @return
     */
    List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     *
     *
     * @param operIds ID
     * @return
     */
    int deleteOperLogByIds(Long[] operIds);

    /**
     *
     *
     * @param operId ID
     * @return
     */
    SysOperLog selectOperLogById(Long operId);

    /**
     *
     */
    void cleanOperLog();
}
