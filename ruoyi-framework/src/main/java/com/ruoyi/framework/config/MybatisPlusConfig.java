package com.ruoyi.framework.config;

import cn.hutool.core.net.NetUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.ruoyi.framework.handler.CreateAndUpdateMetaObjectHandler;
import com.ruoyi.framework.interceptor.PlusDataPermissionInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus()
 *
 * @author Lion Li
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan("${mybatis-plus.mapperPackage}")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //
        interceptor.addInnerInterceptor(dataPermissionInterceptor());
        //
        interceptor.addInnerInterceptor(paginationInnerInterceptor());
        //
        interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        return interceptor;
    }

    /**
     *
     */
    public PlusDataPermissionInterceptor dataPermissionInterceptor() {
        return new PlusDataPermissionInterceptor();
    }

    /**
     * ，
     */
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // ， 500 ，-1
        paginationInnerInterceptor.setMaxLimit(-1L);
        //
        paginationInnerInterceptor.setOverflow(true);
        return paginationInnerInterceptor;
    }

    /**
     *
     */
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     *
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new CreateAndUpdateMetaObjectHandler();
    }

    /**
     *
     * ID
     */
    @Bean
    public IdentifierGenerator idGenerator() {
        return new DefaultIdentifierGenerator(NetUtil.getLocalhost());
    }

    /**
     * PaginationInnerInterceptor ，
     * https://baomidou.com/pages/97710a/
     * OptimisticLockerInnerInterceptor
     * https://baomidou.com/pages/0d93c0/
     * MetaObjectHandler
     * https://baomidou.com/pages/4c6bcf/
     * ISqlInjector sql
     * https://baomidou.com/pages/42ea4a/
     * BlockAttackInnerInterceptor ，
     * https://baomidou.com/pages/f9a237/
     * IllegalSQLInnerInterceptor sql(SQL)
     * IdentifierGenerator
     * https://baomidou.com/pages/568eb2/
     * TenantLineInnerInterceptor
     * https://baomidou.com/pages/aef2f2/
     * DynamicTableNameInnerInterceptor
     * https://baomidou.com/pages/2a45ff/
     */

}
