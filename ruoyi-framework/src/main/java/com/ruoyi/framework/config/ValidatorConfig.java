package com.ruoyi.framework.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;
import java.util.Properties;

/**
 *
 * @author Lion Li
 */
@Configuration
public class ValidatorConfig {

    @Autowired
    private MessageSource messageSource;

    /**
     *
     */
    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        //
        factoryBean.setValidationMessageSource(messageSource);
        //  HibernateValidator
        factoryBean.setProviderClass(HibernateValidator.class);
        Properties properties = new Properties();
        //
        properties.setProperty("hibernate.validator.fail_fast", "true");
        factoryBean.setValidationProperties(properties);
        //
        factoryBean.afterPropertiesSet();
        return factoryBean.getValidator();
    }

}
