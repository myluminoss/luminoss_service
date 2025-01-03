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
import com.ruoyi.system.domain.bo.AiTransactionLogBo;
import com.ruoyi.system.domain.vo.AiTransactionLogVo;
import com.ruoyi.system.domain.AiTransactionLog;
import com.ruoyi.system.mapper.AiTransactionLogMapper;
import com.ruoyi.system.service.IAiTransactionLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * AiTransactionLogService
 *
 * @author ruoyi
 * @date 2024-12-27
 */
@RequiredArgsConstructor
@Service
public class AiTransactionLogServiceImpl implements IAiTransactionLogService {

    private final AiTransactionLogMapper baseMapper;

    /**
     * AiTransactionLog
     */
    @Override
    public AiTransactionLogVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * AiTransactionLog
     */
    @Override
    public TableDataInfo<AiTransactionLogVo> queryPageList(AiTransactionLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiTransactionLog> lqw = buildQueryWrapper(bo);
        lqw.orderByDesc(AiTransactionLog::getCreateTime);
        Page<AiTransactionLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * AiTransactionLog
     */
    @Override
    public List<AiTransactionLogVo> queryList(AiTransactionLogBo bo) {
        LambdaQueryWrapper<AiTransactionLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AiTransactionLog> buildQueryWrapper(AiTransactionLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AiTransactionLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAiAgentId() != null, AiTransactionLog::getAiAgentId, bo.getAiAgentId());
        lqw.eq(StringUtils.isNotBlank(bo.getAccount()), AiTransactionLog::getAccount, bo.getAccount());
        lqw.eq(StringUtils.isNotBlank(bo.getAvatar()), AiTransactionLog::getAvatar, bo.getAvatar());
        lqw.eq(bo.getAvatarIndex() != null, AiTransactionLog::getAvatarIndex, bo.getAvatarIndex());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), AiTransactionLog::getType, bo.getType());
        lqw.eq(bo.getSol() != null, AiTransactionLog::getSol, bo.getSol());
        lqw.eq(StringUtils.isNotBlank(bo.getToken()), AiTransactionLog::getToken, bo.getToken());
        lqw.eq(StringUtils.isNotBlank(bo.getTransaction()), AiTransactionLog::getTransaction, bo.getTransaction());
        return lqw;
    }

    /**
     * AiTransactionLog
     */
    @Override
    public Boolean insertByBo(AiTransactionLogBo bo) {
        AiTransactionLog add = BeanUtil.toBean(bo, AiTransactionLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * AiTransactionLog
     */
    @Override
    public Boolean updateByBo(AiTransactionLogBo bo) {
        AiTransactionLog update = BeanUtil.toBean(bo, AiTransactionLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     */
    private void validEntityBeforeSave(AiTransactionLog entity){
        //TODO
    }

    /**
     * AiTransactionLog
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
