package com.ruoyi.common.core.domain.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 *
 *
 * @author Lion Li
 */

@Data
public class EmailLoginBody {

    /**
     *
     */
    @NotBlank(message = "{user.email.not.blank}")
    @Email(message = "{user.email.not.valid}")
    private String email;

    /**
     * code
     */
    @NotBlank(message = "{email.code.not.blank}")
    private String emailCode;

}
