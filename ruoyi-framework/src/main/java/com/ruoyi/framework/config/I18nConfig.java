package com.ruoyi.framework.config;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 *
 * @author Lion Li
 */
@Configuration
public class I18nConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }

    /**
     */
    static class I18nLocaleResolver implements LocaleResolver {

        @Override
        public Locale resolveLocale(HttpServletRequest httpServletRequest) {
            Locale locale = Locale.getDefault();

            String language = httpServletRequest.getHeader("content-language");
            if (StrUtil.isNotBlank(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            } else {
                String configLanguage = SpringUtils.getBean(Environment.class).getProperty("ruoyi.language", "");
                if (StrUtil.isNotBlank(configLanguage)) {
                    String[] split = configLanguage.split("_");
                    locale = new Locale(split[0], split[1]);
                }
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

        }
    }
}
