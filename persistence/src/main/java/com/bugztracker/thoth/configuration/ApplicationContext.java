package com.bugztracker.thoth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Oleh_Osyka
 * Date: 13.02.2016
 * Time: 13:12
 */
@Configuration
public class ApplicationContext {

    @Bean
    public PropertySourcesPlaceholderConfigurer initPropertiesConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("db.properties"));
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }
}
