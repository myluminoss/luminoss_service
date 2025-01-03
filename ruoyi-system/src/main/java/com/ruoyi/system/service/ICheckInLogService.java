package com.ruoyi.system.service;


import com.ruoyi.system.domain.CheckInConfig;
import com.ruoyi.system.domain.vo.CheckInLogVo;
import com.ruoyi.system.domain.bo.CheckInLogBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * Service
 *
 * @author ruoyi
 * @date 2024-12-04
 */
public interface ICheckInLogService {


    String getCheckInContract();

    List<CheckInConfig> getCheckInConfig();

    /**
     *
     *
     * @param bo
     * @return
     */
    boolean checkIn(CheckInLogBo bo);

    /**
     *
     *
     * @return
     */
    List<CheckInLogVo> getCheckInInfo();

    /**
     *
     */
    CheckInLogVo queryById(Long id);

    CheckInLogVo queryByHash(String hash);

    /**
     *
     */
    TableDataInfo<CheckInLogVo> queryPageList(CheckInLogBo bo, PageQuery pageQuery);

    /**
     *
     */
    List<CheckInLogVo> queryList(CheckInLogBo bo);

    /**
     *
     */
    Boolean insertByBo(CheckInLogBo bo);

    /**
     *
     */
    Boolean updateByBo(CheckInLogBo bo);

    /**
     *
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
