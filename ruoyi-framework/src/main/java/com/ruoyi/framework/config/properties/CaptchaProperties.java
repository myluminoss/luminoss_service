package com.ruoyi.framework.config.properties;

import com.ruoyi.common.enums.CaptchaCategory;
import com.ruoyi.common.enums.CaptchaType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Lion Li
 */
@Data
@Component
@ConfigurationProperties(prefix = "captcha")
public class CaptchaProperties {

    /**
     *
     */
    private CaptchaType type;

    /**
     *
     */
    private CaptchaCategory category;

    /**
     *
     */
    private Integer numberLength;

    /**
     *
     */
    private Integer charLength;
}
