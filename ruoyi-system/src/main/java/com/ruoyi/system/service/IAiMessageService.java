package com.ruoyi.system.service;

import com.ruoyi.system.domain.AiMessage;
import com.ruoyi.system.domain.vo.AiMessageVo;
import com.ruoyi.system.domain.bo.AiMessageBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * AiService
 *
 * @author ruoyi
 * @date 2024-12-23
 */
public interface IAiMessageService {

    /**
     * Ai
     */
    AiMessageVo queryById(Long id);

    /**
     * Ai
     */
    TableDataInfo<AiMessageVo> queryPageList(AiMessageBo bo, PageQuery pageQuery);

    /**
     * Ai
     */
    List<AiMessageVo> queryList(AiMessageBo bo);

    /**
     * Ai
     */
    Boolean insertByBo(AiMessageBo bo);

    /**
     * Ai
     */
    Boolean updateByBo(AiMessageBo bo);

    /**
     * Ai
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
