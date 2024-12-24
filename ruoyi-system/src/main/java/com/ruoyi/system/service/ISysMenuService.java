package com.ruoyi.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.system.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysMenuService {

    /**
     *
     *
     * @param userId ID
     * @return
     */
    List<SysMenu> selectMenuList(Long userId);

    /**
     *
     *
     * @param menu
     * @param userId ID
     * @return
     */
    List<SysMenu> selectMenuList(SysMenu menu, Long userId);

    /**
     * ID
     *
     * @param userId ID
     * @return
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * ID
     *
     * @param roleId ID
     * @return
     */
    Set<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * ID
     *
     * @param userId ID
     * @return
     */
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    /**
     * ID
     *
     * @param roleId ID
     * @return
     */
    List<Long> selectMenuListByRoleId(Long roleId);

    /**
     *
     *
     * @param menus
     * @return
     */
    List<RouterVo> buildMenus(List<SysMenu> menus);

    /**
     *
     *
     * @param menus
     * @return
     */
    List<Tree<Long>> buildMenuTreeSelect(List<SysMenu> menus);

    /**
     * ID
     *
     * @param menuId ID
     * @return
     */
    SysMenu selectMenuById(Long menuId);

    /**
     *
     *
     * @param menuId ID
     * @return  true  false
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     *
     *
     * @param menuId ID
     * @return  true  false
     */
    boolean checkMenuExistRole(Long menuId);

    /**
     *
     *
     * @param menu
     * @return
     */
    int insertMenu(SysMenu menu);

    /**
     *
     *
     * @param menu
     * @return
     */
    int updateMenu(SysMenu menu);

    /**
     *
     *
     * @param menuId ID
     * @return
     */
    int deleteMenuById(Long menuId);

    /**
     *
     *
     * @param menu
     * @return
     */
    boolean checkMenuNameUnique(SysMenu menu);
}
