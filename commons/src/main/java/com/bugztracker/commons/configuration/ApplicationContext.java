package com.bugztracker.commons.configuration;

import com.bugztracker.commons.validators.IPersistenceObjectValidator;
import com.bugztracker.commons.validators.impl.PersistenceObjectValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
public class ApplicationContext {

    @Bean
    public IPersistenceObjectValidator initObjecteValidator() {
        return new PersistenceObjectValidator();
    }

    @Bean(name = "persistenceValidator")
    public Validator initPersistenceValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
