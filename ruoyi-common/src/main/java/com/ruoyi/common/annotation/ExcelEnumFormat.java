package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 *
 *
 * @author Liang
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelEnumFormat {

    /**
     *
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * code，code
     */
    String codeField() default "code";

    /**
     * text，text
     */
    String textField() default "text";

}
