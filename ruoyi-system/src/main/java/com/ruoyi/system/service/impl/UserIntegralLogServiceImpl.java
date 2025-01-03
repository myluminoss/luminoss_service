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
import com.ruoyi.system.domain.bo.UserIntegralLogBo;
import com.ruoyi.system.domain.vo.UserIntegralLogVo;
import com.ruoyi.system.domain.UserIntegralLog;
import com.ruoyi.system.mapper.UserIntegralLogMapper;
import com.ruoyi.system.service.IUserIntegralLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * user integral logService
 *
 * @author ruoyi
 * @date 2024-12-25
 */
@RequiredArgsConstructor
@Service
public class UserIntegralLogServiceImpl implements IUserIntegralLogService {

    private final UserIntegralLogMapper baseMapper;

    /**
     * user integral log
     */
    @Override
    public UserIntegralLogVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * user integral log
     */
    @Override
    public TableDataInfo<UserIntegralLogVo> queryPageList(UserIntegralLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UserIntegralLog> lqw = buildQueryWrapper(bo);
        Page<UserIntegralLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * user integral log
     */
    @Override
    public List<UserIntegralLogVo> queryList(UserIntegralLogBo bo) {
        LambdaQueryWrapper<UserIntegralLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UserIntegralLog> buildQueryWrapper(UserIntegralLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UserIntegralLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, UserIntegralLog::getUserId, bo.getUserId());
        lqw.eq(bo.getIntegral() != null, UserIntegralLog::getIntegral, bo.getIntegral());
        return lqw;
    }

    /**
     * user integral log
     */
    @Override
    public Boolean insertByBo(UserIntegralLogBo bo) {
        UserIntegralLog add = BeanUtil.toBean(bo, UserIntegralLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * user integral log
     */
    @Override
    public Boolean updateByBo(UserIntegralLogBo bo) {
        UserIntegralLog update = BeanUtil.toBean(bo, UserIntegralLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     */
    private void validEntityBeforeSave(UserIntegralLog entity){
        //TODO
    }

    /**
     * user integral log
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
