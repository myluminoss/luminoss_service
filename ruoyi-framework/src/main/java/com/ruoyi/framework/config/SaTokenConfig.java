package com.ruoyi.framework.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.config.properties.SecurityProperties;
import com.ruoyi.framework.handler.AllUrlHandler;
import com.ruoyi.framework.satoken.dao.PlusSaTokenDao;
import com.ruoyi.framework.satoken.service.SaPermissionImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * sa-token
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Slf4j
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    private final SecurityProperties securityProperties;

    /**
     * sa-token
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // ,
        registry.addInterceptor(new SaInterceptor(handler -> {
            AllUrlHandler allUrlHandler = SpringUtils.getBean(AllUrlHandler.class);
            //  --
            SaRouter
                //
                .match(allUrlHandler.getUrls())
                //
                .check(() -> {
                    //  token
                    StpUtil.checkLogin();

                    //
                    // if (log.isDebugEnabled()) {
                    //     log.info(": {}", StpUtil.getTokenTimeout());
                    //     log.info(": {}", StpUtil.getTokenActiveTimeout());
                    // }

                });
        })).addPathPatterns("/**")
            //
            .excludePathPatterns(securityProperties.getExcludes());
    }

    @Bean
    public StpLogic getStpLogicJwt() {
        // Sa-Token  jwt ()
        return new StpLogicJwtForSimple();
    }

    /**
     * (bean)
     */
    @Bean
    public StpInterface stpInterface() {
        return new SaPermissionImpl();
    }

    /**
     * dao
     */
    @Bean
    public SaTokenDao saTokenDao() {
        return new PlusSaTokenDao();
    }

}
