package com.ruoyi.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.service.ISysPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
public class SysPostServiceImpl implements ISysPostService {

    private final SysPostMapper baseMapper;
    private final SysUserPostMapper userPostMapper;

    @Override
    public TableDataInfo<SysPost> selectPagePostList(SysPost post, PageQuery pageQuery) {
        LambdaQueryWrapper<SysPost> lqw = new LambdaQueryWrapper<SysPost>()
            .like(StringUtils.isNotBlank(post.getPostCode()), SysPost::getPostCode, post.getPostCode())
            .eq(StringUtils.isNotBlank(post.getStatus()), SysPost::getStatus, post.getStatus())
            .like(StringUtils.isNotBlank(post.getPostName()), SysPost::getPostName, post.getPostName());
        Page<SysPost> page = baseMapper.selectPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }

    /**
     *
     *
     * @param post
     * @return
     */
    @Override
    public List<SysPost> selectPostList(SysPost post) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysPost>()
            .like(StringUtils.isNotBlank(post.getPostCode()), SysPost::getPostCode, post.getPostCode())
            .eq(StringUtils.isNotBlank(post.getStatus()), SysPost::getStatus, post.getStatus())
            .like(StringUtils.isNotBlank(post.getPostName()), SysPost::getPostName, post.getPostName()));
    }

    /**
     *
     *
     * @return
     */
    @Override
    public List<SysPost> selectPostAll() {
        return baseMapper.selectList();
    }

    /**
     * ID
     *
     * @param postId ID
     * @return
     */
    @Override
    public SysPost selectPostById(Long postId) {
        return baseMapper.selectById(postId);
    }

    /**
     * ID
     *
     * @param userId ID
     * @return ID
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId) {
        return baseMapper.selectPostListByUserId(userId);
    }

    /**
     *
     *
     * @param post
     * @return
     */
    @Override
    public boolean checkPostNameUnique(SysPost post) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysPost>()
            .eq(SysPost::getPostName, post.getPostName())
            .ne(ObjectUtil.isNotNull(post.getPostId()), SysPost::getPostId, post.getPostId()));
        return !exist;
    }

    /**
     *
     *
     * @param post
     * @return
     */
    @Override
    public boolean checkPostCodeUnique(SysPost post) {
        boolean exist = baseMapper.exists(new LambdaQueryWrapper<SysPost>()
            .eq(SysPost::getPostCode, post.getPostCode())
            .ne(ObjectUtil.isNotNull(post.getPostId()), SysPost::getPostId, post.getPostId()));
        return !exist;
    }

    /**
     * ID
     *
     * @param postId ID
     * @return
     */
    @Override
    public long countUserPostById(Long postId) {
        return userPostMapper.selectCount(new LambdaQueryWrapper<SysUserPost>().eq(SysUserPost::getPostId, postId));
    }

    /**
     *
     *
     * @param postId ID
     * @return
     */
    @Override
    public int deletePostById(Long postId) {
        return baseMapper.deleteById(postId);
    }

    /**
     *
     *
     * @param postIds ID
     * @return
     */
    @Override
    public int deletePostByIds(Long[] postIds) {
        for (Long postId : postIds) {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0) {
                throw new ServiceException(String.format("%1$s，!", post.getPostName()));
            }
        }
        return baseMapper.deleteBatchIds(Arrays.asList(postIds));
    }

    /**
     *
     *
     * @param post
     * @return
     */
    @Override
    public int insertPost(SysPost post) {
        return baseMapper.insert(post);
    }

    /**
     *
     *
     * @param post
     * @return
     */
    @Override
    public int updatePost(SysPost post) {
        return baseMapper.updateById(post);
    }
}
