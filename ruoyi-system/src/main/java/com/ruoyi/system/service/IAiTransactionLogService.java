package com.ruoyi.system.service;

import com.ruoyi.system.domain.AiTransactionLog;
import com.ruoyi.system.domain.vo.AiTransactionLogVo;
import com.ruoyi.system.domain.bo.AiTransactionLogBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * AiTransactionLogService
 *
 * @author ruoyi
 * @date 2024-12-27
 */
public interface IAiTransactionLogService {

    /**
     * AiTransactionLog
     */
    AiTransactionLogVo queryById(Long id);

    /**
     *
     */
    TableDataInfo<AiTransactionLogVo> queryPageList(AiTransactionLogBo bo, PageQuery pageQuery);

    /**
     *
     */
    List<AiTransactionLogVo> queryList(AiTransactionLogBo bo);

    /**
     * AiTransactionLog
     */
    Boolean insertByBo(AiTransactionLogBo bo);

    /**
     * AiTransactionLog
     */
    Boolean updateByBo(AiTransactionLogBo bo);

    /**
     * AiTransactionLog
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
