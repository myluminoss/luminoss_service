package com.ruoyi.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author Lion Li
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    /**
     * (ms)，
     */
    int interval() default 5000;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     *    {code}
     */
    String message() default "{repeat.submit.message}";

}
