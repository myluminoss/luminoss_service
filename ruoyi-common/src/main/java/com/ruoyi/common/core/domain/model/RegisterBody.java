package com.ruoyi.common.core.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 *
 * @author Lion Li
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterBody extends LoginBody {

    private String userType;

}
