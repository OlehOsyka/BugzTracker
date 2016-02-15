package com.bugztracker.persistence.configuration;

import com.bugztracker.commons.validators.IPersistenceObjectValidator;
import com.bugztracker.commons.validators.impl.PersistenceObjectValidator;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by Oleh_Osyka
 * Date: 13.02.2016
 * Time: 13:12
 */
@Configuration
public class ApplicationContext {

    @Bean
    public static PropertySourcesPlaceholderConfigurer initPropertiesConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("db.properties"));
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

    @Bean
    public MongoOperations mongoDbFactory(@Value("${db.name}") String dbName,
                                          @Value("${db.host}") String dbHost) throws Exception {
        return new MongoTemplate(new MongoClient(dbHost), dbName);
    }

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
