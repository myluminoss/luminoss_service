package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysPost;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysPostService {


    TableDataInfo<SysPost> selectPagePostList(SysPost post, PageQuery pageQuery);

    /**
     *
     *
     * @param post
     * @return
     */
    List<SysPost> selectPostList(SysPost post);

    /**
     *
     *
     * @return
     */
    List<SysPost> selectPostAll();

    /**
     * ID
     *
     * @param postId ID
     * @return
     */
    SysPost selectPostById(Long postId);

    /**
     * ID
     *
     * @param userId ID
     * @return ID
     */
    List<Long> selectPostListByUserId(Long userId);

    /**
     *
     *
     * @param post
     * @return
     */
    boolean checkPostNameUnique(SysPost post);

    /**
     *
     *
     * @param post
     * @return
     */
    boolean checkPostCodeUnique(SysPost post);

    /**
     * ID
     *
     * @param postId ID
     * @return
     */
    long countUserPostById(Long postId);

    /**
     *
     *
     * @param postId ID
     * @return
     */
    int deletePostById(Long postId);

    /**
     *
     *
     * @param postIds ID
     * @return
     */
    int deletePostByIds(Long[] postIds);

    /**
     *
     *
     * @param post
     * @return
     */
    int insertPost(SysPost post);

    /**
     *
     *
     * @param post
     * @return
     */
    int updatePost(SysPost post);
}
