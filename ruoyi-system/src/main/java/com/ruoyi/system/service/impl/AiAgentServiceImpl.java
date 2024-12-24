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
import com.ruoyi.system.domain.bo.AiAgentBo;
import com.ruoyi.system.domain.vo.AiAgentVo;
import com.ruoyi.system.domain.AiAgent;
import com.ruoyi.system.mapper.AiAgentMapper;
import com.ruoyi.system.service.IAiAgentService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * AiService
 *
 * @author ruoyi
 * @date 2024-12-22
 */
@RequiredArgsConstructor
@Service
public class AiAgentServiceImpl implements IAiAgentService {

    private final AiAgentMapper baseMapper;

    /**
     * Ai
     */
    @Override
    public AiAgentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * Ai
     */
    @Override
    public TableDataInfo<AiAgentVo> queryPageList(AiAgentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiAgent> lqw = buildQueryWrapper(bo);
        Page<AiAgentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * Ai
     */
    @Override
    public List<AiAgentVo> queryList(AiAgentBo bo) {
        LambdaQueryWrapper<AiAgent> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AiAgent> buildQueryWrapper(AiAgentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AiAgent> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, AiAgent::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), AiAgent::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), AiAgent::getTitle, bo.getTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getTicker()), AiAgent::getTicker, bo.getTicker());
        lqw.eq(StringUtils.isNotBlank(bo.getImg()), AiAgent::getImg, bo.getImg());
        lqw.eq(StringUtils.isNotBlank(bo.getDesc()), AiAgent::getDesc, bo.getDesc());
        return lqw;
    }

    /**
     * Ai
     */
    @Override
    public Boolean insertByBo(AiAgentBo bo) {
        AiAgent add = BeanUtil.toBean(bo, AiAgent.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * Ai
     */
    @Override
    public Boolean updateByBo(AiAgentBo bo) {
        AiAgent update = BeanUtil.toBean(bo, AiAgent.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     *
     */
    private void validEntityBeforeSave(AiAgent entity){
        //TODO ,
    }

    /**
     * Ai
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO ,
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
