package com.ruoyi.system.service;


import com.ruoyi.system.domain.vo.AiAgentVo;
import com.ruoyi.system.domain.bo.AiAgentBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * AiService
 *
 * @author ruoyi
 * @date 2024-12-22
 */
public interface IAiAgentService {

    AiAgentVo getAgentDetails(Long id);

    TableDataInfo<AiAgentVo> getAgentList(AiAgentBo bo, PageQuery pageQuery);

    /**
     * create ai agent
     * @param bo agent info
     * @return res
     */
    AiAgentBo createAgent(AiAgentBo bo);

    /**
     * Ai
     */
    AiAgentVo queryById(Long id);

    /**
     * Ai
     */
    TableDataInfo<AiAgentVo> queryPageList(AiAgentBo bo, PageQuery pageQuery);

    /**
     * Ai
     */
    List<AiAgentVo> queryList(AiAgentBo bo);

    /**
     * Ai
     */
    Boolean insertByBo(AiAgentBo bo);

    /**
     * Ai
     */
    Boolean updateByBo(AiAgentBo bo);

    /**
     * Ai
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
