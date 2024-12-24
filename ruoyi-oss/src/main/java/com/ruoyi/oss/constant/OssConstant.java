package com.ruoyi.oss.constant;

import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author Lion Li
 */
public interface OssConstant {

    /**
     *
     */
    String DEFAULT_CONFIG_KEY = "sys_oss:default_config";

    /**
     */
    String PEREVIEW_LIST_RESOURCE_KEY = "sys.oss.previewListResource";

    /**
     */
    List<Long> SYSTEM_DATA_IDS = Arrays.asList(1L, 2L, 3L, 4L);

    /**
     */
    String[] CLOUD_SERVICE = new String[] {"aliyun", "qcloud", "qiniu", "obs"};

    /**
     * https
     */
    String IS_HTTPS = "Y";

}
