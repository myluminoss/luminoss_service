package com.ruoyi.web.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@RestController
public class SysIndexController {

    /**
     *
     */
    private final RuoYiConfig ruoyiConfig;

    /**
     * ,
     */
    @SaIgnore
    @GetMapping("/")
    public String index() {
        return StringUtils.format("{},:v{},.", ruoyiConfig.getName(), ruoyiConfig.getVersion());
    }
}
