package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 *  ({@link com.ruoyi.common.translation.TranslationInterface} )
 *
 * @author Lion Li
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface TranslationType {

    /**
     *
     */
    String type();

}
