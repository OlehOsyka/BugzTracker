package com.bugztracker.thoth.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 * Created by Oleh_Osyka
 * Date: 13.02.2016
 * Time: 12:58
 */
@Configuration
public class MongoContext extends AbstractMongoConfiguration{

    @Value("${db.name}")
    private String dbName;
    @Value("${db.host}")
    private String dbHost;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(dbHost);
    }
}
