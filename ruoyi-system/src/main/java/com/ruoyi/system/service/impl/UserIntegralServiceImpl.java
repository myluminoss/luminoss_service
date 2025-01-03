package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.domain.bo.UserIntegralLogBo;
import com.ruoyi.system.service.IUserIntegralLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.UserIntegralBo;
import com.ruoyi.system.domain.vo.UserIntegralVo;
import com.ruoyi.system.domain.UserIntegral;
import com.ruoyi.system.mapper.UserIntegralMapper;
import com.ruoyi.system.service.IUserIntegralService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * user integralService
 *
 * @author ruoyi
 * @date 2024-12-25
 */
@RequiredArgsConstructor
@Service
public class UserIntegralServiceImpl implements IUserIntegralService {

    private final UserIntegralMapper baseMapper;

    private final IUserIntegralLogService iUserIntegralLogService;

    @Transactional
    public boolean income(long userId, long integral, String flag, String remark) {
        if (integral < 0) {
            throw new ServiceException("The points amount must be greater than 0");
        }
        UserIntegralVo userIntegralVo = queryByUserId(userId);
        if (userIntegralVo == null) {
            UserIntegral userIntegral = new UserIntegral();
            userIntegral.setUserId(userId);
            userIntegral.setIntegral(0L);
            if (baseMapper.insert(userIntegral) > 0) {
                userIntegralVo = queryByUserId(userId);
            } else {
                throw new ServiceException("Failed to create points account");
            }
        }
        UserIntegralBo up = new UserIntegralBo();
        up.setId(userIntegralVo.getId());
        up.setIntegral(userIntegralVo.getIntegral() + integral);
        boolean res = updateByBo(up);
        if (!res) {
            throw new ServiceException("Update points failed");
        }

        // insert integral log
        UserIntegralLogBo userIntegralLogBo = new UserIntegralLogBo();
        userIntegralLogBo.setUserId(userId);
        userIntegralLogBo.setIntegral(up.getIntegral());
        userIntegralLogBo.setChange(integral);
        userIntegralLogBo.setFlag(flag);
        userIntegralLogBo.setType(1);
        userIntegralLogBo.setRemark(remark);
        boolean lRes = iUserIntegralLogService.insertByBo(userIntegralLogBo);
        if (!lRes) {
            throw new ServiceException("Update points failed");
        }
        return true;
    }

    /**
     * user integral
     */
    @Override
    public UserIntegralVo queryByUserId(Long userId){
        UserIntegralBo query = new UserIntegralBo();
        query.setUserId(userId);
        LambdaQueryWrapper<UserIntegral> lqw = buildQueryWrapper(query);

        return baseMapper.selectVoOne(lqw);
    }

    /**
     * user integral
     */
    @Override
    public UserIntegralVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * user integral
     */
    @Override
    public TableDataInfo<UserIntegralVo> queryPageList(UserIntegralBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UserIntegral> lqw = buildQueryWrapper(bo);
        Page<UserIntegralVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * user integral
     */
    @Override
    public List<UserIntegralVo> queryList(UserIntegralBo bo) {
        LambdaQueryWrapper<UserIntegral> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UserIntegral> buildQueryWrapper(UserIntegralBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UserIntegral> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, UserIntegral::getUserId, bo.getUserId());
        lqw.eq(bo.getIntegral() != null, UserIntegral::getIntegral, bo.getIntegral());
        return lqw;
    }

    /**
     * user integral
     */
    @Override
    public Boolean insertByBo(UserIntegralBo bo) {
        UserIntegral add = BeanUtil.toBean(bo, UserIntegral.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * user integral
     */
    @Override
    public Boolean updateByBo(UserIntegralBo bo) {
        UserIntegral update = BeanUtil.toBean(bo, UserIntegral.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     */
    private void validEntityBeforeSave(UserIntegral entity){
        //TODO
    }

    /**
     * user integral
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
