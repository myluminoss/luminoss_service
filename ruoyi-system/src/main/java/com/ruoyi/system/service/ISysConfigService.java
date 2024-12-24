package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysConfig;

import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface ISysConfigService {


    TableDataInfo<SysConfig> selectPageConfigList(SysConfig config, PageQuery pageQuery);

    /**
     *
     *
     * @param configId ID
     * @return
     */
    SysConfig selectConfigById(Long configId);

    /**
     *
     *
     * @param configKey
     * @return
     */
    String selectConfigByKey(String configKey);

    /**
     *
     *
     * @return true，false
     */
    boolean selectCaptchaEnabled();

    /**
     *
     *
     * @param config
     * @return
     */
    List<SysConfig> selectConfigList(SysConfig config);

    /**
     *
     *
     * @param config
     * @return
     */
    String insertConfig(SysConfig config);

    /**
     *
     *
     * @param config
     * @return
     */
    String updateConfig(SysConfig config);

    /**
     *
     *
     * @param configIds ID
     */
    void deleteConfigByIds(Long[] configIds);

    /**
     *
     */
    void loadingConfigCache();

    /**
     *
     */
    void clearConfigCache();

    /**
     *
     */
    void resetConfigCache();

    /**
     *
     *
     * @param config
     * @return
     */
    boolean checkConfigKeyUnique(SysConfig config);

}
