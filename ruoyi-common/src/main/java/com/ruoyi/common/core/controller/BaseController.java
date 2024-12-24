package com.ruoyi.common.core.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;

/**
 * web
 *
 * @author Lion Li
 */
public class BaseController {

    /**
     *
     *
     * @param rows
     * @return
     */
    protected R<Void> toAjax(int rows) {
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     *
     *
     * @param result
     * @return
     */
    protected R<Void> toAjax(boolean result) {
        return result ? R.ok() : R.fail();
    }

    /**
     *
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     *
     */
    public LoginUser getLoginUser() {
        return LoginHelper.getLoginUser();
    }

    /**
     * id
     */
    public Long getUserId() {
        return LoginHelper.getUserId();
    }

    /**
     * id
     */
    public Long getDeptId() {
        return LoginHelper.getDeptId();
    }

    /**
     *
     */
    public String getUsername() {
        return LoginHelper.getUsername();
    }
}
