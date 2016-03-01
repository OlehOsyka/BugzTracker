package com.bugztracker.commons.configuration;

import com.bugztracker.commons.validators.ICommonsValidator;
import com.bugztracker.commons.validators.impl.CommonsEntityValidator;
import com.bugztracker.commons.validators.impl.CommonsPersistenceValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by Oleh_Osyka
 * Date: 13.02.2016
 * Time: 13:12
 */
@Configuration
@ComponentScan("com.bugztracker.commons")
public class CommonsApplicationContext {

    @Bean(name = "commonsPersistenceValidator")
    public ICommonsValidator provideCommonsPersistenceValidator() {
        return new CommonsPersistenceValidator();
    }

    @Bean(name = "commonsEntityValidator")
    public ICommonsValidator provideCommonsEntityValidator() {
        return new CommonsEntityValidator();
    }

    @Bean(name = "persistenceValidator")
    public Validator providePersistenceValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @Bean(name = "entityValidator")
    public Validator provideEntityValidator() {
        return new LocalValidatorFactoryBean();
    }
}
