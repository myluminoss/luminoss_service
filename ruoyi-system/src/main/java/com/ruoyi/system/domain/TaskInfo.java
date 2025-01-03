package com.ruoyi.system.domain;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskInfo {

    List<Object> quests = new ArrayList<>();

    List<CheckInInfo> signs;

    private boolean todaySign;

    private Integer signIndex;

    private String signAddress;

    @Data
    public static class CheckInInfo {

        private Long id;

        private String name;

        private Integer earn;

        private Integer status;

        private Long day;

    }
}
