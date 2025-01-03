package com.ruoyi.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.helper.DataBaseHelper;
import com.ruoyi.common.utils.StreamUtils;
import com.ruoyi.system.domain.SysRoleDept;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysRoleDeptMapper;
import com.ruoyi.system.service.ISysDataScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * <p>
 * : Service``
 * : deptMapper.selectList  selectList ``
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service("sdss")
public class SysDataScopeServiceImpl implements ISysDataScopeService {

    private final SysRoleDeptMapper roleDeptMapper;
    private final SysDeptMapper deptMapper;

    @Override
    public String getRoleCustom(Long roleId) {
        List<SysRoleDept> list = roleDeptMapper.selectList(
            new LambdaQueryWrapper<SysRoleDept>()
                .select(SysRoleDept::getDeptId)
                .eq(SysRoleDept::getRoleId, roleId));
        if (CollUtil.isNotEmpty(list)) {
            return StreamUtils.join(list, rd -> Convert.toStr(rd.getDeptId()));
        }
        return null;
    }

    @Override
    public String getDeptAndChild(Long deptId) {
        List<SysDept> deptList = deptMapper.selectList(new LambdaQueryWrapper<SysDept>()
            .select(SysDept::getDeptId)
            .apply(DataBaseHelper.findInSet(deptId, "ancestors")));
        List<Long> ids = StreamUtils.toList(deptList, SysDept::getDeptId);
        ids.add(deptId);
        if (CollUtil.isNotEmpty(ids)) {
            return StreamUtils.join(ids, Convert::toStr);
        }
        return null;
    }

}
