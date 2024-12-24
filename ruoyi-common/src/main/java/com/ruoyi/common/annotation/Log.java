package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 *
 *
 * @author ruoyi
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     *
     */
    String title() default "";

    /**
     *
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     *
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     *
     */
    boolean isSaveRequestData() default true;

    /**
     *
     */
    boolean isSaveResponseData() default true;

    /**
     *
     */
    String[] excludeParamNames() default {};

}
