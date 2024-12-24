package com.ruoyi.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static final String SEPARATOR = ",";

    public static String blankToDefault(String str, String defaultValue) {
        return StrUtil.blankToDefault(str, defaultValue);
    }

    /**
     * *
     *
     * @param str String
     * @return true： false：
     */
    public static boolean isEmpty(String str) {
        return StrUtil.isEmpty(str);
    }

    /**
     * *
     *
     * @param str String
     * @return true： false：
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     *
     */
    public static String trim(String str) {
        return StrUtil.trim(str);
    }

    /**
     *
     *
     * @param str
     * @param start
     * @return
     */
    public static String substring(final String str, int start) {
        return substring(str, start, str.length());
    }

    /**
     *
     *
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static String substring(final String str, int start, int end) {
        return StrUtil.sub(str, start, end);
    }

    /**
     * , {} <br>
     *  {} <br>
     *  {}  \\ { ， {}  \  \\\\ <br>
     * ：<br>
     * ：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * {}： format("this is \\{} for {}", "a", "b") -> this is {} for a<br>
     * \： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template ， {}
     * @param params
     * @return
     */
    public static String format(String template, Object... params) {
        return StrUtil.format(template, params);
    }

    /**
     * http(s)://
     *
     * @param link
     * @return
     */
    public static boolean ishttp(String link) {
        return Validator.isUrl(link);
    }

    /**
     * set
     *
     * @param str
     * @param sep
     * @return set
     */
    public static Set<String> str2Set(String str, String sep) {
        return new HashSet<>(str2List(str, sep, true, false));
    }

    /**
     * list
     *
     * @param str
     * @param sep
     * @param filterBlank
     * @param trim
     * @return list
     */
    public static List<String> str2List(String str, String sep, boolean filterBlank, boolean trim) {
        List<String> list = new ArrayList<>();
        if (isEmpty(str)) {
            return list;
        }

        //
        if (filterBlank && isBlank(str)) {
            return list;
        }
        String[] split = str.split(sep);
        for (String string : split) {
            if (filterBlank && isBlank(string)) {
                continue;
            }
            if (trim) {
                string = trim(string);
            }
            list.add(string);
        }

        return list;
    }

    /**
     *
     *
     * @param cs
     * @param searchCharSequences
     * @return
     */
    public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCharSequences) {
        return StrUtil.containsAnyIgnoreCase(cs, searchCharSequences);
    }

    /**
     *
     */
    public static String toUnderScoreCase(String str) {
        return StrUtil.toUnderlineCase(str);
    }

    /**
     *
     *
     * @param str
     * @param strs
     * @return true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        return StrUtil.equalsAnyIgnoreCase(str, strs);
    }

    /**
     * 。，。 ：HELLO_WORLD->HelloWorld
     *
     * @param name
     * @return
     */
    public static String convertToCamelCase(String name) {
        return StrUtil.upperFirst(StrUtil.toCamelCase(name));
    }

    /**
     *  ：user_name->userName
     */
    public static String toCamelCase(String s) {
        return StrUtil.toCamelCase(s);
    }

    /**
     *
     *
     * @param str
     * @param strs
     * @return
     */
    public static boolean matches(String str, List<String> strs) {
        if (isEmpty(str) || CollUtil.isEmpty(strs)) {
            return false;
        }
        for (String pattern : strs) {
            if (isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * url:
     * ? ;
     * * ，;
     * ** ;
     *
     * @param pattern
     * @param url     url
     */
    public static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    /**
     * 0，。，，size， size。
     *
     * @param num
     * @param size
     * @return ，。
     */
    public static String padl(final Number num, final int size) {
        return padl(num.toString(), size, '0');
    }

    /**
     * 。ssize，size。
     *
     * @param s
     * @param size
     * @param c
     * @return ，。
     */
    public static String padl(final String s, final int size, final char c) {
        final StringBuilder sb = new StringBuilder(size);
        if (s != null) {
            final int len = s.length();
            if (s.length() <= size) {
                for (int i = size - len; i > 0; i--) {
                    sb.append(c);
                }
                sb.append(s);
            } else {
                return s.substring(len - size, len);
            }
        } else {
            for (int i = size; i > 0; i--) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * ()
     *
     * @param str
     * @return
     */
    public static List<String> splitList(String str) {
        return splitTo(str, Convert::toStr);
    }

    /**
     *
     *
     * @param str
     * @param separator
     * @return
     */
    public static List<String> splitList(String str, String separator) {
        return splitTo(str, separator, Convert::toStr);
    }

    /**
     * ()
     *
     * @param str
     * @param mapper
     * @return
     */
    public static <T> List<T> splitTo(String str, Function<? super Object, T> mapper) {
        return splitTo(str, SEPARATOR, mapper);
    }

    /**
     *
     *
     * @param str
     * @param separator
     * @param mapper
     * @return
     */
    public static <T> List<T> splitTo(String str, String separator, Function<? super Object, T> mapper) {
        if (isBlank(str)) {
            return new ArrayList<>(0);
        }
        return StrUtil.split(str, separator)
            .stream()
            .filter(Objects::nonNull)
            .map(mapper)
            .collect(Collectors.toList());
    }

}
