package com.ruoyi.system.service;

import com.ruoyi.system.domain.UserToken;
import com.ruoyi.system.domain.vo.UserTokenVo;
import com.ruoyi.system.domain.bo.UserTokenBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * Service
 *
 * @author ruoyi
 * @date 2024-12-05
 */
public interface IUserTokenService {

    /**
     *
     */
    UserTokenVo queryById(Long id);

    UserTokenVo queryByToken(String token);

    /**
     *
     */
    TableDataInfo<UserTokenVo> queryPageList(UserTokenBo bo, PageQuery pageQuery);

    /**
     *
     */
    List<UserTokenVo> queryList(UserTokenBo bo);

    /**
     *
     */
    Boolean insertByBo(UserTokenBo bo);

    /**
     *
     */
    Boolean updateByBo(UserTokenBo bo);

    /**
     *
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
