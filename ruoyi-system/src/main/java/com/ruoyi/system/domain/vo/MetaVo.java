package com.ruoyi.system.domain.vo;

import com.ruoyi.common.utils.StringUtils;
import lombok.Data;

/**
 *
 *
 * @author ruoyi
 */

@Data
public class MetaVo {

    /**
     *
     */
    private String title;

    /**
     * ,src/assets/icons/svg
     */
    private String icon;

    /**
     * true, <keep-alive>
     */
    private boolean noCache;

    /**
     * （http(s)://）
     */
    private String link;

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public MetaVo(String title, String icon, boolean noCache) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public MetaVo(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public MetaVo(String title, String icon, boolean noCache, String link) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        if (StringUtils.ishttp(link)) {
            this.link = link;
        }
    }

}
