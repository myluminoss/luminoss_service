package com.ruoyi.system.mapper;

import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.system.domain.SysPost;

import java.util.List;

public interface SysPostMapper extends BaseMapperPlus<SysPostMapper, SysPost, SysPost> {

    List<Long> selectPostListByUserId(Long userId);

    List<SysPost> selectPostsByUserName(String userName);

}
