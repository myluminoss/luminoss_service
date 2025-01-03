package com.ruoyi.common.core.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 *
 * @author Lion Li
 */

@Data
@NoArgsConstructor
public class RoleDTO implements Serializable {

    /**
     * ID
     */
    private Long roleId;

    /**
     *
     */
    private String roleName;

    /**
     *
     */
    private String roleKey;

    /**
     * （1:；2:；3:；4:；5:）
     */
    private String dataScope;

}
