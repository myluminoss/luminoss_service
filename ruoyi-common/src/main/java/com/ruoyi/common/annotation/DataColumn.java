package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 *
 *
 *
 *
 * @author Lion Li
 * @version 3.5.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataColumn {

    /**
     *
     */
    String[] key() default "deptName";

    /**
     *
     */
    String[] value() default "dept_id";

}
