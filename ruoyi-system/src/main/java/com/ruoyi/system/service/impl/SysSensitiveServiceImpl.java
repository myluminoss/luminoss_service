package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.service.SensitiveService;
import com.ruoyi.common.helper.LoginHelper;
import org.springframework.stereotype.Service;

/**
 *
 *
 *
 *
 * @author Lion Li
 * @version 3.6.0
 */
@Service
public class SysSensitiveServiceImpl implements SensitiveService {

    /**
     *
     */
    @Override
    public boolean isSensitive() {
        return !LoginHelper.isAdmin();
    }

}
