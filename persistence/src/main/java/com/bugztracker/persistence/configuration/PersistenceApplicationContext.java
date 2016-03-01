package com.bugztracker.persistence.configuration;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by Oleh_Osyka
 * Date: 13.02.2016
 * Time: 13:12
 */
@Configuration
@ComponentScan(basePackages = "com.bugztracker.persistence")
public class PersistenceApplicationContext {

    @Bean(name = "persistenceProperties")
    public static PropertySourcesPlaceholderConfigurer initPropertiesConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setPlaceholderPrefix("${persistence.");
        configurer.setLocations(new ClassPathResource("db.properties"));
        return configurer;
    }

    @Bean
    public MongoOperations mongoDbFactory(@Value("${persistence.db.name}") String dbName,
                                          @Value("${persistence.db.host}") String dbHost) throws Exception {
        return new MongoTemplate(new MongoClient(dbHost), dbName);
    }

}
