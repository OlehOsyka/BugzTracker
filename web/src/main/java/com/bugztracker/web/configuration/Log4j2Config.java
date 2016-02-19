package com.bugztracker.web.configuration;

import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;

import java.net.URI;

import static org.apache.logging.log4j.Level.*;
import static org.apache.logging.log4j.core.Filter.Result.*;


/**
 * Created by Oleh_Osyka
 * Date: 15.02.2016
 * Time: 11:07
 */
@Plugin(name = "CustomConfigurationFactory", category = ConfigurationFactory.CATEGORY)
@Order(1)
public class Log4j2Config extends ConfigurationFactory {

    static Configuration createConfiguration(final String name, ConfigurationBuilder<BuiltConfiguration> builder) {
        builder.setConfigurationName(name);
        builder.setStatusLevel(ERROR);
        builder.add(builder.newFilter("ThresholdFilter", ACCEPT, NEUTRAL).
                addAttribute("level", INFO));

        AppenderComponentBuilder appenderBuilder = builder.newAppender("Stdout", "CONSOLE").
                addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout").
                addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable"));
        appenderBuilder.add(builder.newFilter("MarkerFilter", DENY, NEUTRAL).
                addAttribute("marker", "FLOW"));

        builder.add(appenderBuilder);
        builder.add(builder.newLogger("org.apache.logging.log4j", DEBUG).
                add(builder.newAppenderRef("Stdout")).
                addAttribute("additivity", false));
        builder.add(builder.newRootLogger(ERROR).
                add(builder.newAppenderRef("Stdout")));
        return builder.build();
    }

    @Override
    public Configuration getConfiguration(ConfigurationSource source) {
        return getConfiguration(source.toString(), null);
    }

    @Override
    public Configuration getConfiguration(final String name, final URI configLocation) {
        ConfigurationBuilder<BuiltConfiguration> builder = newConfigurationBuilder();
        return createConfiguration(name, builder);
    }

    @Override
    protected String[] getSupportedTypes() {
        return new String[]{"*"};
    }
}
