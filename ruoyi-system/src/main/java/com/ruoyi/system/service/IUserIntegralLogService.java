package com.ruoyi.system.service;


import com.ruoyi.system.domain.vo.UserIntegralLogVo;
import com.ruoyi.system.domain.bo.UserIntegralLogBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * user integral logService
 *
 * @author ruoyi
 * @date 2024-12-25
 */
public interface IUserIntegralLogService {

    /**
     * user integral log
     */
    UserIntegralLogVo queryById(Long id);

    /**
     *
     */
    TableDataInfo<UserIntegralLogVo> queryPageList(UserIntegralLogBo bo, PageQuery pageQuery);

    /**
     *
     */
    List<UserIntegralLogVo> queryList(UserIntegralLogBo bo);

    /**
     * user integral log
     */
    Boolean insertByBo(UserIntegralLogBo bo);

    /**
     * user integral log
     */
    Boolean updateByBo(UserIntegralLogBo bo);

    /**
     * user integral log
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
