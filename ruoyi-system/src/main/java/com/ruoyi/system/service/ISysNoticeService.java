package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysNotice;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysNoticeService {


    TableDataInfo<SysNotice> selectPageNoticeList(SysNotice notice, PageQuery pageQuery);

    /**
     *
     *
     * @param noticeId ID
     * @return
     */
    SysNotice selectNoticeById(Long noticeId);

    /**
     *
     *
     * @param notice
     * @return
     */
    List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     *
     *
     * @param notice
     * @return
     */
    int insertNotice(SysNotice notice);

    /**
     *
     *
     * @param notice
     * @return
     */
    int updateNotice(SysNotice notice);

    /**
     *
     *
     * @param noticeId ID
     * @return
     */
    int deleteNoticeById(Long noticeId);

    /**
     *
     *
     * @param noticeIds ID
     * @return
     */
    int deleteNoticeByIds(Long[] noticeIds);
}
