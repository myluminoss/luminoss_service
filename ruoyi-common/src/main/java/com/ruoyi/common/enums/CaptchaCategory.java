package com.ruoyi.common.enums;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Lion Li
 */
@Getter
@AllArgsConstructor
public enum CaptchaCategory {

    /**
     */
    LINE(LineCaptcha.class),

    /**
     */
    CIRCLE(CircleCaptcha.class),

    /**
     */
    SHEAR(ShearCaptcha.class);

    private final Class<? extends AbstractCaptcha> clazz;
}
