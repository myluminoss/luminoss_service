package com.ruoyi.common.annotation;

import com.ruoyi.common.utils.StringUtils;

import java.lang.annotation.*;

/**
 *
 *
 * @author Lion Li
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelDictFormat {

    /**
     * ,type (: sys_user_sex)
     */
    String dictType() default "";

    /**
     *  (: 0=,1=,2=)
     */
    String readConverterExp() default "";

    /**
     * ,
     */
    String separator() default StringUtils.SEPARATOR;

}
