package com.ruoyi.common.core.domain.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author Lion Li
 */

@Data
public class SmsLoginBody {

    /**
     *
     */
    @NotBlank(message = "{user.phonenumber.not.blank}")
    private String phonenumber;

    /**
     * code
     */
    @NotBlank(message = "{sms.code.not.blank}")
    private String smsCode;

}
