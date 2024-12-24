package com.ruoyi.common.constant;

/**
 * key
 *
 * @author ruoyi
 */
public interface CacheConstants {

    /**
     *  redis key
     */
    String ONLINE_TOKEN_KEY = "online_tokens:";

    /**
     *  redis key
     */
    String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     *  cache key
     */
    String SYS_CONFIG_KEY = "sys_config:";

    /**
     *  cache key
     */
    String SYS_DICT_KEY = "sys_dict:";

    /**
     *  redis key
     */
    String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     *  redis key
     */
    String RATE_LIMIT_KEY = "rate_limit:";

    /**
     *  redis key
     */
    String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
}
