package com.ruoyi.common.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpStatus;
import com.ruoyi.common.constant.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ruoyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServletUtils extends ServletUtil {

    /**
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     */
    public static Boolean getParameterToBool(String name) {
        return Convert.toBool(getRequest().getParameter(name));
    }

    /**
     */
    public static Boolean getParameterToBool(String name, Boolean defaultValue) {
        return Convert.toBool(getRequest().getParameter(name), defaultValue);
    }

    public static Map<String, String[]> getParams(ServletRequest request) {
        final Map<String, String[]> map = request.getParameterMap();
        return Collections.unmodifiableMap(map);
    }

    public static Map<String, String> getParamMap(ServletRequest request) {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : getParams(request).entrySet()) {
            params.put(entry.getKey(), StringUtils.join(entry.getValue(), StringUtils.SEPARATOR));
        }
        return params;
    }

    /**
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(HttpStatus.HTTP_OK);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        if (accept != null && accept.contains(MediaType.APPLICATION_JSON_VALUE)) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.equalsAnyIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        return StringUtils.equalsAnyIgnoreCase(ajax, "json", "xml");
    }

    public static String getClientIP() {
        return getClientIP(getRequest());
    }

    /**
     *
     *
     * @param str
     * @return
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     *
     *
     * @param str
     * @return
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

}
