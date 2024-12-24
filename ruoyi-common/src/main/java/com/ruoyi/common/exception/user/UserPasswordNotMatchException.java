package com.ruoyi.common.exception.user;

/**
 *
 *
 * @author ruoyi
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match");
    }
}
