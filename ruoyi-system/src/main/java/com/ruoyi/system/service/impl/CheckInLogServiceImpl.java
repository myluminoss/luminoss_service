package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.json.JSONObject;
import com.ruoyi.common.core.domain.model.LoginUser;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.utils.redis.QueueUtils;
import com.ruoyi.system.domain.CheckInConfig;

import com.ruoyi.system.service.IUserIntegralService;
import com.ruoyi.system.service.SolscanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.CheckInLogBo;
import com.ruoyi.system.domain.vo.CheckInLogVo;
import com.ruoyi.system.domain.CheckInLog;
import com.ruoyi.system.mapper.CheckInLogMapper;
import com.ruoyi.system.service.ICheckInLogService;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Service
 *
 * @author ruoyi
 * @date 2024-12-04
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class CheckInLogServiceImpl implements ICheckInLogService {

    private final CheckInLogMapper baseMapper;

    private final SolscanService solscanService;

    private final IUserIntegralService iUserIntegralService;

    private final String checkInContract = "DMZYWqWALA6X9TS283rCy21ch1DHWzPHF4kCrFY9ogpg";

    private final String queueName = "checkInLog";

    public String getCheckInContract() {
        return checkInContract;
    }

    /**
     * subscribe check in log
     */
    @PostConstruct
    public void subscribeCheckInLog() {
        QueueUtils.subscribeBlockingQueue(queueName, (CheckInLogBo bo) -> {
            try {
                JSONObject jsonObject = solscanService.queryTransactionStatusByHash(bo.getHash());
                if (ObjUtil.isNotNull(jsonObject)) {
                    Long time = jsonObject.getJSONObject("result").getLong("blockTime");
                    boolean isContract = jsonObject.getJSONObject("result")
                        .getJSONObject("transaction")
                        .getJSONObject("message")
                        .getJSONArray("instructions")
                        .stream().map(i -> ((JSONObject) i).getStr("programId"))
                        .anyMatch(checkInContract::equals);

                    // valid time 60 minutes
                    if (time * 1000 > System.currentTimeMillis() - 1000 * 60 * 60 && isContract) {
                        CheckInLogBo up = new CheckInLogBo();
                        up.setId(bo.getId());
                        up.setStatus("success");
                        updateByBo(up);
                        return;
                    } else {
                        CheckInLogBo up = new CheckInLogBo();
                        up.setId(bo.getId());
                        up.setStatus("fail");
                        updateByBo(up);
                        return;
                    }
                }
            } catch (Exception e) {
                log.error("check in log error", e);
            }
            try {
                if (bo.getCreateTime().before(DateUtil.offsetMinute(new Date(), -1))) {
                    CheckInLogBo up = new CheckInLogBo();
                    up.setId(bo.getId());
                    up.setStatus("fail");
                    updateByBo(up);
                } else {
                    // retry
                    QueueUtils.addDelayedQueueObject(queueName, bo, 10, TimeUnit.SECONDS);
                }
            } catch (Exception ignored) {}
        }, true);
    }

    /**
     * get check in config list
     *
     * @return  config list
     */
    public List<CheckInConfig> getCheckInConfig() {
        List<CheckInConfig> checkInConfigs = new ArrayList<>();

        CheckInConfig checkInConfig1 = new CheckInConfig();
        checkInConfig1.setId(1L);
        checkInConfig1.setStatus("none");
        checkInConfig1.setName("FIRST");
        checkInConfig1.setTimes(1L);
        checkInConfig1.setIntegral(15);

        CheckInConfig checkInConfig2 = new CheckInConfig();
        checkInConfig2.setId(2L);
        checkInConfig2.setStatus("none");
        checkInConfig2.setName("SECOND");
        checkInConfig2.setTimes(2L);
        checkInConfig2.setIntegral(15);

        CheckInConfig checkInConfig3 = new CheckInConfig();
        checkInConfig3.setId(3L);
        checkInConfig3.setStatus("none");
        checkInConfig3.setName("THIRD");
        checkInConfig3.setTimes(3L);
        checkInConfig3.setIntegral(20);

        CheckInConfig checkInConfig4 = new CheckInConfig();
        checkInConfig4.setId(4L);
        checkInConfig4.setStatus("none");
        checkInConfig4.setName("FOURTH");
        checkInConfig4.setTimes(4L);
        checkInConfig4.setIntegral(20);

        CheckInConfig checkInConfig5 = new CheckInConfig();
        checkInConfig5.setId(5L);
        checkInConfig5.setStatus("none");
        checkInConfig5.setName("FIFTH");
        checkInConfig5.setTimes(5L);
        checkInConfig5.setIntegral(40);

        CheckInConfig checkInConfig6 = new CheckInConfig();
        checkInConfig6.setId(6L);
        checkInConfig6.setStatus("none");
        checkInConfig6.setName("SIXTH");
        checkInConfig6.setTimes(6L);
        checkInConfig6.setIntegral(40);

        CheckInConfig checkInConfig7 = new CheckInConfig();
        checkInConfig7.setId(7L);
        checkInConfig7.setStatus("none");
        checkInConfig7.setName("SEVENTH");
        checkInConfig7.setTimes(7L);
        checkInConfig7.setIntegral(60);

        checkInConfigs.add(checkInConfig1);
        checkInConfigs.add(checkInConfig2);
        checkInConfigs.add(checkInConfig3);
        checkInConfigs.add(checkInConfig4);
        checkInConfigs.add(checkInConfig5);
        checkInConfigs.add(checkInConfig6);
        checkInConfigs.add(checkInConfig7);
        return checkInConfigs;
    }

    /**
     * get check in config by day
     *
     * @return  config
     */
    public CheckInConfig getCheckInConfigByDay(long day) {
        List<CheckInConfig> checkInConfigs = getCheckInConfig();
        return checkInConfigs.stream()
            .filter(checkInConfig -> checkInConfig.getTimes().equals(day))
            .findFirst()
            .orElse(null);
    }

    /**
     * check in
     *
     * @param bo CheckInLogBo
     * @return  check in result
     */
    public boolean checkIn(CheckInLogBo bo) {
        // user info
        LoginUser loginUser = LoginHelper.getLoginUser();

        // query last check in
        LambdaQueryWrapper<CheckInLog> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CheckInLog::getUserId, loginUser.getUserId());
        lqw.orderByDesc(CheckInLog::getCreateTime);
        lqw.ne(CheckInLog::getStatus, "fail");
        lqw.last("limit 1");
        CheckInLogVo lastCheckInLog = baseMapper.selectVoOne(lqw);
        if (ObjUtil.isNull(lastCheckInLog)) {
            bo.setTimes(1L);
        } else {
            // If it is today
            if (DateUtil.isSameDay(lastCheckInLog.getCreateTime(), new Date())) {
                throw new ServiceException("Signed in today");
            }
            if (lastCheckInLog.getTimes() >= 7) {
                bo.setTimes(1L);
            } else {
                bo.setTimes(lastCheckInLog.getTimes() + 1L);
            }
        }

        // Earn points
        CheckInConfig checkInConfig = getCheckInConfigByDay(bo.getTimes());
        if (ObjUtil.isNotNull(checkInConfig)) {
            bo.setIntegral(checkInConfig.getIntegral());
        } else {
            bo.setIntegral(15);
        }

        iUserIntegralService.income(loginUser.getUserId(), bo.getIntegral(), "checkIn", "Check in");

        bo.setUserId(loginUser.getUserId());
        bo.setStatus("wait");
        boolean res = insertByBo(bo);

        bo.setCreateTime(new Date());
        QueueUtils.addDelayedQueueObject(queueName, bo, 3, TimeUnit.SECONDS);
        return res;
    }

    /**
     * get check in info
     *
     * @return List<CheckInLogVo>
     */
    public List<CheckInLogVo> getCheckInInfo() {
        // select last 30
        LambdaQueryWrapper<CheckInLog> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CheckInLog::getUserId, LoginHelper.getUserId());
        lqw.orderByDesc(CheckInLog::getCreateTime);
        lqw.ne(CheckInLog::getStatus, "fail");
        lqw.last("limit 30");

        List<CheckInLogVo> checkInLogs = baseMapper.selectVoList(lqw);

        List<CheckInLogVo> recent = new ArrayList<>();
        if (checkInLogs.size() > 0) {
            CheckInLogVo lastLog = checkInLogs.get(0);
            if (!(lastLog.getTimes() >= 7 && !DateUtil.isSameDay(lastLog.getCreateTime(), new Date()))) {
                for (CheckInLogVo checkInLogVo : checkInLogs) {
                    recent.add(checkInLogVo);
                    if (checkInLogVo.getTimes() <= 1) {
                        break;
                    }
                }
            }
        }

        // check in config
        List<CheckInConfig> checkInConfigs = getCheckInConfig();
        List<CheckInLogVo> res = new ArrayList<>();
        for (CheckInConfig checkInConfig: checkInConfigs) {
            CheckInLogVo checkInLogVo = recent.stream().filter(i -> i.getTimes().equals(checkInConfig.getTimes())).findFirst().orElse(null);
            if (ObjUtil.isNull(checkInLogVo)) {
                checkInLogVo = new CheckInLogVo();
                checkInLogVo.setTimes(checkInConfig.getTimes());
                checkInLogVo.setIntegral(checkInConfig.getIntegral());
                checkInLogVo.setStatus("none");
                checkInLogVo.setName(checkInConfig.getName());
            } else {
                checkInLogVo.setName(checkInConfig.getName());
            }
            res.add(checkInLogVo);
        }
        return res;
    }

    /**
     * query by id
     */
    @Override
    public CheckInLogVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * query by hash
     */
    @Override
    public CheckInLogVo queryByHash(String hash){
        CheckInLogBo query = new CheckInLogBo();
        query.setHash(hash);
        LambdaQueryWrapper<CheckInLog> lqw = buildQueryWrapper(query);
        lqw.last("limit 1");
        return baseMapper.selectVoOne(lqw);
    }

    /**
     * list
     */
    @Override
    public TableDataInfo<CheckInLogVo> queryPageList(CheckInLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CheckInLog> lqw = buildQueryWrapper(bo);
        Page<CheckInLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * list
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
     * list
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
     * update
     */
    @Override
    public Boolean updateByBo(CheckInLogBo bo) {
        CheckInLog update = BeanUtil.toBean(bo, CheckInLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * valid entity before save
     */
    private void validEntityBeforeSave(CheckInLog entity){
        //TODO ,
    }

    /**
     * delete
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO ,
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
