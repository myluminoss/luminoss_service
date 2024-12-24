package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.bo.SysOssConfigBo;
import com.ruoyi.system.domain.vo.SysOssConfigVo;

import java.util.Collection;

/**
 * Service
 *
 * @author Lion Li
 * @author
 * @date 2021-08-13
 */
public interface ISysOssConfigService {

    /**
     * OSS
     */
    void init();

    /**
     *
     */
    SysOssConfigVo queryById(Long ossConfigId);

    /**
     *
     */
    TableDataInfo<SysOssConfigVo> queryPageList(SysOssConfigBo bo, PageQuery pageQuery);


    /**
     *
     *
     * @param bo
     * @return
     */
    Boolean insertByBo(SysOssConfigBo bo);

    /**
     *
     *
     * @param bo
     * @return
     */
    Boolean updateByBo(SysOssConfigBo bo);

    /**
     *
     *
     * @param ids
     * @param isValid ,true-,false-
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     *
     */
    int updateOssConfigStatus(SysOssConfigBo bo);

}
