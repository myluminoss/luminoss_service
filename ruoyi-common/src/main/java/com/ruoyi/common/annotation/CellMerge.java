package com.ruoyi.common.annotation;

import com.ruoyi.common.excel.CellMergeStrategy;

import java.lang.annotation.*;

/**
 * excel ()
 *
 *  {@link CellMergeStrategy}
 *
 * @author Lion Li
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CellMerge {

	/**
	 * col index
	 */
	int index() default -1;

}
