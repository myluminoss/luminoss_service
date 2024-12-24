package com.ruoyi.common.exception.user;

/**
 *
 *
 * @author ruoyi
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error");
    }
}
