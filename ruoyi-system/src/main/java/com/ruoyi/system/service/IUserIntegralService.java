package com.ruoyi.system.service;


import com.ruoyi.system.domain.vo.UserIntegralVo;
import com.ruoyi.system.domain.bo.UserIntegralBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * user integralService
 *
 * @author ruoyi
 * @date 2024-12-25
 */
public interface IUserIntegralService {

    boolean income(long userId, long integral, String flag, String remark);

    /**
     * user integral
     */
    UserIntegralVo queryById(Long id);

    UserIntegralVo queryByUserId(Long userId);

    /**
     *
     */
    TableDataInfo<UserIntegralVo> queryPageList(UserIntegralBo bo, PageQuery pageQuery);

    /**
     *
     */
    List<UserIntegralVo> queryList(UserIntegralBo bo);

    /**
     * user integral
     */
    Boolean insertByBo(UserIntegralBo bo);

    /**
     * user integral
     */
    Boolean updateByBo(UserIntegralBo bo);

    /**
     * user integral
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
