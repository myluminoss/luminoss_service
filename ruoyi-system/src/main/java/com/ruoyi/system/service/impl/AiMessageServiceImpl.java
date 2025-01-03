package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.AiMessageBo;
import com.ruoyi.system.domain.vo.AiMessageVo;
import com.ruoyi.system.domain.AiMessage;
import com.ruoyi.system.mapper.AiMessageMapper;
import com.ruoyi.system.service.IAiMessageService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * AiService
 *
 * @author ruoyi
 * @date 2024-12-23
 */
@RequiredArgsConstructor
@Service
public class AiMessageServiceImpl implements IAiMessageService {

    private final AiMessageMapper baseMapper;

    /**
     * Ai query by id
     */
    @Override
    public AiMessageVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * Ai list
     */
    @Override
    public TableDataInfo<AiMessageVo> queryPageList(AiMessageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiMessage> lqw = buildQueryWrapper(bo);
        lqw.orderByDesc(AiMessage::getCreateTime);
        Page<AiMessageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * Ai list
     */
    @Override
    public List<AiMessageVo> queryList(AiMessageBo bo) {
        LambdaQueryWrapper<AiMessage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AiMessage> buildQueryWrapper(AiMessageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AiMessage> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAiAgentId() != null, AiMessage::getAiAgentId, bo.getAiAgentId());
        lqw.eq(bo.getUserId() != null, AiMessage::getUserId, bo.getUserId());
        lqw.eq(bo.getSessionId() != null, AiMessage::getSessionId, bo.getSessionId());
        lqw.eq(StringUtils.isNotBlank(bo.getData()), AiMessage::getData, bo.getData());
        return lqw;
    }

    /**
     * Ai insert
     */
    @Override
    public Boolean insertByBo(AiMessageBo bo) {
        AiMessage add = BeanUtil.toBean(bo, AiMessage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * Ai update
     */
    @Override
    public Boolean updateByBo(AiMessageBo bo) {
        AiMessage update = BeanUtil.toBean(bo, AiMessage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * valid Entity Before Save
     */
    private void validEntityBeforeSave(AiMessage entity){
        //TODO ,
    }

    /**
     * Ai delete
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO ,
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
