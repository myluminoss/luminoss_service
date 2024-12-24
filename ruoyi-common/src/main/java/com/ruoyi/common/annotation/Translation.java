package com.ruoyi.common.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruoyi.common.translation.handler.TranslationHandler;

import java.lang.annotation.*;

/**
 *
 *
 * @author Lion Li
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@JacksonAnnotationsInside
@JsonSerialize(using = TranslationHandler.class)
public @interface Translation {

    /**
     *  ( {@link com.ruoyi.common.annotation.TranslationType} type)
     * <p>
     *   @{@link Translation#mapper()}
     */
    String type();

    /**
     *  ()
     */
    String mapper() default "";

    /**
     *  : type(sys_user_sex)
     */
    String other() default "";

}
