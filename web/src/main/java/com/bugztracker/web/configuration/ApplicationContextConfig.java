package com.bugztracker.web.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * Author: Yuliia Vovk
 * Date: 20.02.16
 * Time: 10:27
 */
@Configuration
@ComponentScan(basePackages = "com.bugztracker.web", excludeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = Controller.class))
public class ApplicationContextConfig {

    @Bean(name = "entityValidator")
    public Validator provideEntityValidator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer initPropertiesConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("web.properties"));
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}
