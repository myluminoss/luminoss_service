package com.ruoyi.common.constant;

/**
 *
 * <p>
 * key  cacheNames#ttl#maxIdleTime#maxSize
 * <p>
 * ttl  0 0
 * maxIdleTime  LRU 0 0
 * maxSize  LRU 0 0
 * <p>
 * : test#60s、test#0#60s、test#0#1m#1000、test#1h#0#500
 *
 * @author Lion Li
 */
public interface CacheNames {

    /**
     *
     */
    String DEMO_CACHE = "demo:cache#60s#10m#20";

    /**
     *
     */
    String SYS_CONFIG = "sys_config";

    /**
     *
     */
    String SYS_DICT = "sys_dict";

    /**
     *
     */
    String SYS_USER_NAME = "sys_user_name#30d";

    /**
     *
     */
    String SYS_DEPT = "sys_dept#30d";

    /**
     * OSS
     */
    String SYS_OSS = "sys_oss#30d";

    /**
     * OSS
     */
    String SYS_OSS_CONFIG = "sys_oss_config";

    /**
     *
     */
    String ONLINE_TOKEN = "online_tokens";

}
