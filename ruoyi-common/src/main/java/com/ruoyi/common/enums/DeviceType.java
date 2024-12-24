package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceType {


    PC("pc"),

    APP("app"),

    XCX("xcx");

    private final String device;
}
