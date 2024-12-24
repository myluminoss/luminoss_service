package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.service.BaseScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.CheckInLogBo;
import com.ruoyi.system.domain.vo.CheckInLogVo;
import com.ruoyi.system.domain.CheckInLog;
import com.ruoyi.system.mapper.CheckInLogMapper;
import com.ruoyi.system.service.ICheckInLogService;

import java.util.*;

/**
 * Service
 *
 * @author ruoyi
 * @date 2024-12-04
 */
@RequiredArgsConstructor
@Service
public class CheckInLogServiceImpl implements ICheckInLogService {

    private final CheckInLogMapper baseMapper;

    private final BaseScanService baseScanService;

    /**
     *
     *
     * @param bo
     * @return
     */
    public boolean checkIn(CheckInLogBo bo) {
        //
        LoginUser loginUser = LoginHelper.getLoginUser();

        // ，
        LambdaQueryWrapper<CheckInLog> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CheckInLog::getUserId, loginUser.getUserId());
        lqw.orderByDesc(CheckInLog::getCreateTime);
        lqw.ne(CheckInLog::getStatus, "fail");
        lqw.last("limit 1");
        CheckInLogVo lastCheckInLog = baseMapper.selectVoOne(lqw);
        if (ObjUtil.isNull(lastCheckInLog)) {
            bo.setTimes(1L);
        } else {
            // todo ，


            if (lastCheckInLog.getTimes() >= 7) {
                bo.setTimes(1L);
            } else {
                bo.setTimes(lastCheckInLog.getTimes() + 1L);
            }
        }

        bo.setUserId(loginUser.getUserId());
        bo.setStatus("wait");
        boolean res = insertByBo(bo);

        //
        ThreadUtil.execute(() -> {
            for (int i = 0; i < 5; i++) {
                ThreadUtil.sleep(5000);
                boolean r = baseScanService.queryTransactionStatusByHash(bo.getHash());
                if (r) {
                    CheckInLogBo checkInLogBo = new CheckInLogBo();
                    checkInLogBo.setStatus("success");
                    checkInLogBo.setId(bo.getId());
                    checkInLogBo.setMsg("Sign in successfully");
                    updateByBo(checkInLogBo);
                    return;
                }
            }
        });
        return res;
    }


    /**
     *
     *
     * @return
     */
    public List<CheckInLogVo> getCheckInInfo() {
        //
        LambdaQueryWrapper<CheckInLog> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CheckInLog::getUserId, LoginHelper.getUserId());
        lqw.orderByDesc(CheckInLog::getCreateTime);
        lqw.last("limit 7");

        List<CheckInLogVo> checkInLogs = baseMapper.selectVoList(lqw);

        List<CheckInLogVo> res = new ArrayList<>();
        for (CheckInLogVo checkInLog: checkInLogs) {
            res.add(checkInLog);
            if (Long.valueOf(1L).equals(checkInLog.getTimes())) {
                break;
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**
     *
     */
    @Override
    public CheckInLogVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     *
     */
    @Override
    public CheckInLogVo queryByHash(String hash){
        CheckInLogBo query = new CheckInLogBo();
        query.setHash(hash);
        LambdaQueryWrapper<CheckInLog> lqw = buildQueryWrapper(query);
        return baseMapper.selectVoOne(lqw);
    }

    /**
     *
     */
    @Override
    public TableDataInfo<CheckInLogVo> queryPageList(CheckInLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CheckInLog> lqw = buildQueryWrapper(bo);
        Page<CheckInLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     *
     */
    @Override
    public List<CheckInLogVo> queryList(CheckInLogBo bo) {
        LambdaQueryWrapper<CheckInLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CheckInLog> buildQueryWrapper(CheckInLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CheckInLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, CheckInLog::getUserId, bo.getUserId());
        lqw.eq(bo.getTimes() != null, CheckInLog::getTimes, bo.getTimes());
        lqw.eq(StringUtils.isNotBlank(bo.getHash()), CheckInLog::getHash, bo.getHash());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CheckInLog::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     *
     */
    @Override
    public Boolean insertByBo(CheckInLogBo bo) {
        CheckInLog add = BeanUtil.toBean(bo, CheckInLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     *
     */
    @Override
    public Boolean updateByBo(CheckInLogBo bo) {
        CheckInLog update = BeanUtil.toBean(bo, CheckInLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     *
     */
    private void validEntityBeforeSave(CheckInLog entity){
        //TODO ,
    }

    /**
     *
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO ,
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
