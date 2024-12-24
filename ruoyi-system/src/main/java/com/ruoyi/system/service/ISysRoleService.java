package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysRoleService {


    TableDataInfo<SysRole> selectPageRoleList(SysRole role, PageQuery pageQuery);

    /**
     *
     *
     * @param role
     * @return
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * ID
     *
     * @param userId ID
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * ID
     *
     * @param userId ID
     * @return
     */
    Set<String> selectRolePermissionByUserId(Long userId);

    /**
     *
     *
     * @return
     */
    List<SysRole> selectRoleAll();

    /**
     * ID
     *
     * @param userId ID
     * @return ID
     */
    List<Long> selectRoleListByUserId(Long userId);

    /**
     * ID
     *
     * @param roleId ID
     * @return
     */
    SysRole selectRoleById(Long roleId);

    /**
     *
     *
     * @param role
     * @return
     */
    boolean checkRoleNameUnique(SysRole role);

    /**
     *
     *
     * @param role
     * @return
     */
    boolean checkRoleKeyUnique(SysRole role);

    /**
     *
     *
     * @param role
     */
    void checkRoleAllowed(SysRole role);

    /**
     *
     *
     * @param roleId id
     */
    void checkRoleDataScope(Long roleId);

    /**
     * ID
     *
     * @param roleId ID
     * @return
     */
    long countUserRoleByRoleId(Long roleId);

    /**
     *
     *
     * @param role
     * @return
     */
    int insertRole(SysRole role);

    /**
     *
     *
     * @param role
     * @return
     */
    int updateRole(SysRole role);

    /**
     *
     *
     * @param role
     * @return
     */
    int updateRoleStatus(SysRole role);

    /**
     *
     *
     * @param role
     * @return
     */
    int authDataScope(SysRole role);

    /**
     * ID
     *
     * @param roleId ID
     * @return
     */
    int deleteRoleById(Long roleId);

    /**
     *
     *
     * @param roleIds ID
     * @return
     */
    int deleteRoleByIds(Long[] roleIds);

    /**
     *
     *
     * @param userRole
     * @return
     */
    int deleteAuthUser(SysUserRole userRole);

    /**
     *
     *
     * @param roleId  ID
     * @param userIds ID
     * @return
     */
    int deleteAuthUsers(Long roleId, Long[] userIds);

    /**
     *
     *
     * @param roleId  ID
     * @param userIds ID
     * @return
     */
    int insertAuthUsers(Long roleId, Long[] userIds);

    void cleanOnlineUserByRole(Long roleId);
}
