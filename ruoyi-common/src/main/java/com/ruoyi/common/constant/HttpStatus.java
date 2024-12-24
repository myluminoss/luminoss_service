package com.ruoyi.common.constant;

/**
 *
 *
 * @author Lion Li
 */
public interface HttpStatus {
    /**
     *
     */
    int SUCCESS = 200;

    /**
     *
     */
    int CREATED = 201;

    /**
     *
     */
    int ACCEPTED = 202;

    /**
     * ，
     */
    int NO_CONTENT = 204;

    /**
     *
     */
    int MOVED_PERM = 301;

    /**
     *
     */
    int SEE_OTHER = 303;

    /**
     *
     */
    int NOT_MODIFIED = 304;

    /**
     * （，）
     */
    int BAD_REQUEST = 400;

    /**
     *
     */
    int UNAUTHORIZED = 401;

    /**
     * ，
     */
    int FORBIDDEN = 403;

    /**
     * ，
     */
    int NOT_FOUND = 404;

    /**
     * http
     */
    int BAD_METHOD = 405;

    /**
     * ，
     */
    int CONFLICT = 409;

    /**
     * ，
     */
    int UNSUPPORTED_TYPE = 415;

    /**
     *
     */
    int ERROR = 500;

    /**
     *
     */
    int NOT_IMPLEMENTED = 501;

    /**
     *
     */
    int WARN = 601;
}
