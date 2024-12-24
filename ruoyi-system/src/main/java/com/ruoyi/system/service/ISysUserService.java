package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysUserService {


    TableDataInfo<SysUser> selectPageUserList(SysUser user, PageQuery pageQuery);

    /**
     *
     *
     * @param user
     * @return
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     *
     *
     * @param user
     * @return
     */
    TableDataInfo<SysUser> selectAllocatedList(SysUser user, PageQuery pageQuery);

    /**
     *
     *
     * @param user
     * @return
     */
    TableDataInfo<SysUser> selectUnallocatedList(SysUser user, PageQuery pageQuery);

    /**
     *
     *
     * @param userName
     * @return
     */
    SysUser selectUserByUserName(String userName);

    /**
     *
     *
     * @param phonenumber
     * @return
     */
    SysUser selectUserByPhonenumber(String phonenumber);

    /**
     * ID
     *
     * @param userId ID
     * @return
     */
    SysUser selectUserById(Long userId);

    /**
     * ID
     *
     * @param userName
     * @return
     */
    String selectUserRoleGroup(String userName);

    /**
     * ID
     *
     * @param userName
     * @return
     */
    String selectUserPostGroup(String userName);

    /**
     *
     *
     * @param user
     * @return
     */
    boolean checkUserNameUnique(SysUser user);

    /**
     *
     *
     * @param user
     * @return
     */
    boolean checkPhoneUnique(SysUser user);

    /**
     * email
     *
     * @param user
     * @return
     */
    boolean checkEmailUnique(SysUser user);

    /**
     *
     *
     * @param user
     */
    void checkUserAllowed(SysUser user);

    /**
     *
     *
     * @param userId id
     */
    void checkUserDataScope(Long userId);

    /**
     *
     *
     * @param user
     * @return
     */
    int insertUser(SysUser user);

    /**
     *
     *
     * @param user
     * @return
     */
    boolean registerUser(SysUser user);

    /**
     *
     *
     * @param user
     * @return
     */
    int updateUser(SysUser user);

    /**
     *
     *
     * @param userId  ID
     * @param roleIds
     */
    void insertUserAuth(Long userId, Long[] roleIds);

    /**
     *
     *
     * @param user
     * @return
     */
    int updateUserStatus(SysUser user);

    /**
     *
     *
     * @param user
     * @return
     */
    int updateUserProfile(SysUser user);

    /**
     *
     *
     * @param userName
     * @param avatar
     * @return
     */
    boolean updateUserAvatar(String userName, String avatar);

    /**
     *
     *
     * @param user
     * @return
     */
    int resetPwd(SysUser user);

    /**
     *
     *
     * @param userName
     * @param password
     * @return
     */
    int resetUserPwd(String userName, String password);

    /**
     * ID
     *
     * @param userId ID
     * @return
     */
    int deleteUserById(Long userId);

    /**
     *
     *
     * @param userIds ID
     * @return
     */
    int deleteUserByIds(Long[] userIds);

}
