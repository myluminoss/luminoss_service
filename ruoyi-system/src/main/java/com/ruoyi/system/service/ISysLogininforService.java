package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysLogininfor;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysLogininforService {


    TableDataInfo<SysLogininfor> selectPageLogininforList(SysLogininfor logininfor, PageQuery pageQuery);

    /**
     *
     *
     * @param logininfor
     */
    void insertLogininfor(SysLogininfor logininfor);

    /**
     *
     *
     * @param logininfor
     * @return
     */
    List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    /**
     *
     *
     * @param infoIds ID
     * @return
     */
    int deleteLogininforByIds(Long[] infoIds);

    /**
     *
     */
    void cleanLogininfor();
}
