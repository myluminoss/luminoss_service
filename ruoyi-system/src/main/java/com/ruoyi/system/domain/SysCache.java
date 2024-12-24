package com.ruoyi.system.domain;

import com.ruoyi.common.utils.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author Lion Li
 */
@Data
@NoArgsConstructor
public class SysCache {

    /**
     *
     */
    private String cacheName = "";

    /**
     *
     */
    private String cacheKey = "";

    /**
     */
    private String cacheValue = "";

    /**
     */
    private String remark = "";

    public SysCache(String cacheName, String remark) {
        this.cacheName = cacheName;
        this.remark = remark;
    }

    public SysCache(String cacheName, String cacheKey, String cacheValue) {
        this.cacheName = StringUtils.replace(cacheName, ":", "");
        this.cacheKey = StringUtils.replace(cacheKey, cacheName, "");
        this.cacheValue = cacheValue;
    }

}
