package com.ruoyi.common.core.service;

/**
 *  OSS
 *
 * @author Lion Li
 */
public interface OssService {

    /**
     * ossIdurl
     *
     * @param ossIds ossId
     * @return url
     */
    String selectUrlByIds(String ossIds);

}
