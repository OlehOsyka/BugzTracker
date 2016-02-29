package com.bugztracker.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

/**
 * Author: Yuliia Vovk
 * Date: 20.02.16
 * Time: 10:27
 */
@Configuration
@ComponentScan(basePackages = {"com.bugztracker.web",
        "com.bugztracker.service.configuration",
        "com.bugztracker.commons.configuration",
        "com.bugztracker.persistence.configuration"},
        excludeFilters = @Filter(type = ANNOTATION, value = Controller.class))
public class WebApplicationContextConfig {

    @Bean(name = "entityValidator")
    public Validator provideEntityValidator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer initPropertiesConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("web.properties"));
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}
