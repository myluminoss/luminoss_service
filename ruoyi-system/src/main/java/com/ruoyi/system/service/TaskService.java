package com.ruoyi.system.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.ruoyi.system.domain.TaskInfo;
import com.ruoyi.system.domain.vo.CheckInLogVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Resource
    private ICheckInLogService iCheckInLogService;

    public TaskInfo getTaskInfo() {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTodaySign(false);

        List<TaskInfo.CheckInInfo> signs = new ArrayList<>();
        List<CheckInLogVo> vos = iCheckInLogService.getCheckInInfo();
        for (CheckInLogVo vo : vos) {
            TaskInfo.CheckInInfo checkInInfo = new TaskInfo.CheckInInfo();
            checkInInfo.setId(vo.getId());
            checkInInfo.setDay(vo.getTimes());
            checkInInfo.setEarn(vo.getIntegral());
            checkInInfo.setName(vo.getName());
            checkInInfo.setStatus("wait".equals(vo.getStatus()) || "success".equals(vo.getStatus()) ? 2 : 1);

            signs.add(checkInInfo);

            if (ObjUtil.isNotNull(vo.getCreateTime()) && DateUtil.isSameDay(vo.getCreateTime(), new Date())) {
                taskInfo.setTodaySign(true);
            }
        }

        taskInfo.setSigns(signs);
        taskInfo.setSignIndex(0);
        taskInfo.setSignAddress(iCheckInLogService.getCheckInContract());

        return taskInfo;
    }


}
