package com.ruoyi.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.common.core.domain.entity.SysDept;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysDeptService {
    /**
     *
     *
     * @param dept
     * @return
     */
    List<SysDept> selectDeptList(SysDept dept);

    /**
     *
     *
     * @param dept
     * @return
     */
    List<Tree<Long>> selectDeptTreeList(SysDept dept);

    /**
     *
     *
     * @param depts
     * @return
     */
    List<Tree<Long>> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * ID
     *
     * @param roleId ID
     * @return
     */
    List<Long> selectDeptListByRoleId(Long roleId);

    /**
     * ID
     *
     * @param deptId ID
     * @return
     */
    SysDept selectDeptById(Long deptId);

    /**
     * ID（）
     *
     * @param deptId ID
     * @return
     */
    long selectNormalChildrenDeptById(Long deptId);

    /**
     *
     *
     * @param deptId ID
     * @return
     */
    boolean hasChildByDeptId(Long deptId);

    /**
     *
     *
     * @param deptId ID
     * @return  true  false
     */
    boolean checkDeptExistUser(Long deptId);

    /**
     *
     *
     * @param dept
     * @return
     */
    boolean checkDeptNameUnique(SysDept dept);

    /**
     *
     *
     * @param deptId id
     */
    void checkDeptDataScope(Long deptId);

    /**
     *
     *
     * @param dept
     * @return
     */
    int insertDept(SysDept dept);

    /**
     *
     *
     * @param dept
     * @return
     */
    int updateDept(SysDept dept);

    /**
     *
     *
     * @param deptId ID
     * @return
     */
    int deleteDeptById(Long deptId);
}
