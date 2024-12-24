package com.ruoyi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 *
 * @author Lion Li
 */
@Getter
@AllArgsConstructor
public enum LoginType {

    /**
     *
     */
    PASSWORD("user.password.retry.limit.exceed", "user.password.retry.limit.count"),

    /**
     *
     */
    SMS("sms.code.retry.limit.exceed", "sms.code.retry.limit.count"),

    /**
     *
     */
    EMAIL("email.code.retry.limit.exceed", "email.code.retry.limit.count"),

    /**
     */
    XCX("", "");

    /**
     */
    final String retryLimitExceed;

    /**
     */
    final String retryLimitCount;
}
