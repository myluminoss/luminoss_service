package com.ruoyi.common.utils.ip;

import cn.hutool.core.net.NetUtil;
import cn.hutool.http.HtmlUtil;
import com.ruoyi.common.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author Lion Li
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressUtils {

    //
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        if (StringUtils.isBlank(ip)) {
            return UNKNOWN;
        }
        //
        ip = StringUtils.contains(ip, "0:0:0:0:0:0:0:1") ? "127.0.0.1" : HtmlUtil.cleanHtmlTag(ip);
        if (NetUtil.isInnerIP(ip)) {
            return "IP";
        }
        return RegionUtils.getCityInfo(ip);
    }
}
