package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface SysDictDataMapper extends BaseMapperPlus<SysDictDataMapper, SysDictData, SysDictData> {

    default List<SysDictData> selectDictDataByType(String dictType) {
        return selectList(
            new LambdaQueryWrapper<SysDictData>()
                .eq(SysDictData::getStatus, UserConstants.DICT_NORMAL)
                .eq(SysDictData::getDictType, dictType)
                .orderByAsc(SysDictData::getDictSort));
    }
}
