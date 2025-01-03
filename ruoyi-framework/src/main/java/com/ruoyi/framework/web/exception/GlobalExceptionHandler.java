package com.ruoyi.framework.web.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.DemoModeException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Global exception handler
 *
 * @author Lion Li
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Permission code exception
     */
    @ExceptionHandler(NotPermissionException.class)
    public R<Void> handleNotPermissionException(NotPermissionException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Request uri '{}',Permission code verification failed '{}'", requestURI, e.getMessage());
        return R.fail(HttpStatus.HTTP_FORBIDDEN, "No access rights, please contact the administrator for authorization");
    }

    /**
     * Abnormal role permissions
     */
    @ExceptionHandler(NotRoleException.class)
    public R<Void> handleNotRoleException(NotRoleException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Request uri '{}',Role permission verification failed '{}'", requestURI, e.getMessage());
        return R.fail(HttpStatus.HTTP_FORBIDDEN, "No access rights, please contact the administrator for authorization");
    }

    /**
     * Authentication failed
     */
    @ExceptionHandler(NotLoginException.class)
    public R<Void> handleNotLoginException(NotLoginException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Request uri '{}',Authentication failed '{}',unable to access system resources", requestURI, e.getMessage());
        return R.fail(HttpStatus.HTTP_UNAUTHORIZED, "Authentication failed, unable to access system resources");
    }

    /**
     * The request method is not supported
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Request uri '{}',Not supported '{}' request", requestURI, e.getMethod());
        return R.fail(e.getMessage());
    }

    /**
     * Primary key or UNIQUE index, data duplication exception
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public R<Void> handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Request uri '{}',The record already exists in the database '{}'", requestURI, e.getMessage());
        return R.fail("The record already exists in the database, please contact the administrator to confirm");
    }

    /**
     * Mybatis system exception general processing
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public R<Void> handleCannotFindDataSourceException(MyBatisSystemException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        if (message.contains("CannotFindDataSourceException")) {
            log.error("Request uri '{}', Data source not found", requestURI);
            return R.fail("Data source not found, please contact the administrator for confirmation");
        }
        log.error("Request uri '{}', Mybatis system abnormality", requestURI, e);
        return R.fail(message);
    }

    /**
     * Business exception
     */
    @ExceptionHandler(ServiceException.class)
    public R<Void> handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error(e.getMessage());
        Integer code = e.getCode();
        return ObjectUtil.isNotNull(code) ? R.fail(code, e.getMessage()) : R.fail(e.getMessage());
    }

    /**
     * Business exception
     */
    @ExceptionHandler(BaseException.class)
    public R<Void> handleBaseException(BaseException e, HttpServletRequest request) {
        log.error(e.getMessage());
        return R.fail(e.getMessage());
    }

    /**
     * Required path variable missing in request path
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public R<Void> handleMissingPathVariableException(MissingPathVariableException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Required path variable missing in request path '{}',System abnormality occurred.", requestURI);
        return R.fail(String.format("Required path variable missing in request path [%s]", e.getVariableName()));
    }

    /**
     * Request parameter type does not match
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R<Void> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Request parameter type does not match '{}',System abnormality occurred.", requestURI);
        return R.fail(String.format("The request parameter type does not match,parameter [%s] Requirement type is:'%s',But the input value is:'%s'", e.getName(), e.getRequiredType().getName(), e.getValue()));
    }

    /**
     * Intercept unknown runtime exceptions
     */
    @ExceptionHandler(RuntimeException.class)
    public R<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Request uri '{}',An unknown exception occurred.", requestURI, e);
        return R.fail(e.getMessage());
    }

    /**
     * System abnormality
     */
    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("Request uri '{}',System abnormality occurred.", requestURI, e);
        return R.fail(e.getMessage());
    }

    /**
     * Custom validation exceptions
     */
    @ExceptionHandler(BindException.class)
    public R<Void> handleBindException(BindException e) {
        log.error(e.getMessage());
        String message = StreamUtils.join(e.getAllErrors(), DefaultMessageSourceResolvable::getDefaultMessage, ", ");
        return R.fail(message);
    }

    /**
     * Custom validation exceptions
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R<Void> constraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage());
        String message = StreamUtils.join(e.getConstraintViolations(), ConstraintViolation::getMessage, ", ");
        return R.fail(message);
    }

    /**
     * Custom validation exceptions
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.fail(message);
    }

    /**
     * Demonstration mode exception
     */
    @ExceptionHandler(DemoModeException.class)
    public R<Void> handleDemoModeException(DemoModeException e) {
        return R.fail("Demonstration mode, no operation allowed");
    }
}
