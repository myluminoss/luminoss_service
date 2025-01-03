package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.RandomUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.vo.SysUserSimpleVo;
import com.ruoyi.common.core.service.UserService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.AiAgentBo;
import com.ruoyi.system.domain.vo.AiAgentVo;
import com.ruoyi.system.domain.AiAgent;
import com.ruoyi.system.mapper.AiAgentMapper;
import com.ruoyi.system.service.IAiAgentService;

import java.math.BigDecimal;
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

    private final ISysUserService iSysUserService;

    public AiAgentVo getAgentDetails(Long id) {
        AiAgentVo aiAgentVo = baseMapper.selectVoById(id);

        if (ObjUtil.isNotNull(aiAgentVo)) {
            SysUser user = iSysUserService.selectUserById(aiAgentVo.getUserId());
            SysUserSimpleVo userSimpleVo = new SysUserSimpleVo();
            userSimpleVo.setId(user.getUserId());
            userSimpleVo.setNickname(user.getNickName());
            userSimpleVo.setAvatar(user.getAvatar());
            userSimpleVo.setAvatarIndex(user.getAvatarIndex());
            aiAgentVo.setUser(userSimpleVo);
        }
        return aiAgentVo;
    }

    public TableDataInfo<AiAgentVo> getAgentList(AiAgentBo bo, PageQuery pageQuery) {
        TableDataInfo<AiAgentVo> pageList = queryPageList(bo, pageQuery);

        for (AiAgentVo aiAgentVo : pageList.getRows()) {
            SysUser user = iSysUserService.selectUserById(aiAgentVo.getUserId());
            SysUserSimpleVo userSimpleVo = new SysUserSimpleVo();
            userSimpleVo.setId(user.getUserId());
            userSimpleVo.setNickname(user.getNickName());
            userSimpleVo.setAvatar(user.getAvatar());
            userSimpleVo.setAvatarIndex(user.getAvatarIndex());
            aiAgentVo.setUser(userSimpleVo);
        }
        return pageList;
    }

    public AiAgentBo createAgent(AiAgentBo bo) {
        bo.setTags("Badge,Celestial,Oracle");
        bo.setMarketCap(RandomUtil.randomBigDecimal(BigDecimal.valueOf(0.01), BigDecimal.valueOf(100)));
        bo.setProgress(RandomUtil.randomBigDecimal(BigDecimal.valueOf(0.01), BigDecimal.valueOf(100)));
        bo.setDayChange(RandomUtil.randomBigDecimal(BigDecimal.valueOf(5), BigDecimal.valueOf(100)));
        bo.setTotalTransactions(RandomUtil.randomLong(1000, 100000));
        bo.setInvokeApi(RandomUtil.randomLong(1000, 100000));
        bo.setDecimals(0);
        boolean res = insertByBo(bo);
        if (res) {
            return bo;
        } else {
            throw new RuntimeException("Create Agent Failed");
        }
    }

    /**
     * Ai query by id
     */
    @Override
    public AiAgentVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * Ai list
     */
    @Override
    public TableDataInfo<AiAgentVo> queryPageList(AiAgentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiAgent> lqw = buildQueryWrapper(bo);
        Page<AiAgentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * Ai list
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
     * Ai insert
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
     * Ai update
     */
    @Override
    public Boolean updateByBo(AiAgentBo bo) {
        AiAgent update = BeanUtil.toBean(bo, AiAgent.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * valid entity before save
     */
    private void validEntityBeforeSave(AiAgent entity){
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
