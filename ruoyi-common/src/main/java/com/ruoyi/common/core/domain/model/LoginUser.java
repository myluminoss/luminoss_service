package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.core.domain.dto.RoleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 *
 * @author Lion Li
 */

@Data
@NoArgsConstructor
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long userId;

    /**
     * ID
     */
    private Long deptId;

    /**
     *
     */
    private String deptName;

    /**
     *
     */
    private String token;

    /**
     *
     */
    private String userType;

    /**
     *
     */
    private Long loginTime;

    /**
     *
     */
    private Long expireTime;

    /**
     * IP
     */
    private String ipaddr;

    /**
     *
     */
    private String loginLocation;

    /**
     *
     */
    private String browser;

    /**
     *
     */
    private String os;

    /**
     *
     */
    private Set<String> menuPermission;

    /**
     *
     */
    private Set<String> rolePermission;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private List<RoleDTO> roles;

    /**
     *  ID
     */
    private Long roleId;

    /**
     * id
     */
    public String getLoginId() {
        if (userType == null) {
            throw new IllegalArgumentException("");
        }
        if (userId == null) {
            throw new IllegalArgumentException("ID");
        }
        return userType + ":" + userId;
    }

}
