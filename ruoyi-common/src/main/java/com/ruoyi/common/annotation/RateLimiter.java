package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.LimitType;

import java.lang.annotation.*;

/**
 *
 *
 * @author Lion Li
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * key,Spring el
     *   #code.id #{#code}
     */
    String key() default "";

    /**
     * ,
     */
    int time() default 60;

    /**
     *
     */
    int count() default 100;

    /**
     *
     */
    LimitType limitType() default LimitType.DEFAULT;

    /**
     *    {code}
     */
    String message() default "{rate.limiter.message}";
}
