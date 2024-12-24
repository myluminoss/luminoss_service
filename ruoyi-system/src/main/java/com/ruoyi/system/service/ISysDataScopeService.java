package com.ruoyi.system.service;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysDataScopeService {

    /**
     *
     *
     * @param roleId id
     * @return id
     */
    String getRoleCustom(Long roleId);

    /**
     *
     *
     * @param deptId id
     * @return id
     */
    String getDeptAndChild(Long deptId);

}
