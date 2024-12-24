package com.ruoyi.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author Lion Li
 */
@Configuration
// aop,AopContext
@EnableAspectJAutoProxy(exposeProxy = true)
public class ApplicationConfig {

}
