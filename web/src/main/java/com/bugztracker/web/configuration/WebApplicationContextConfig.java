package com.bugztracker.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

/**
 * Author: Yuliia Vovk
 * Date: 20.02.16
 * Time: 10:27
 */
@Configuration
@ComponentScan(basePackages = {
        "com.bugztracker.web",
        "com.bugztracker.service.configuration",
        "com.bugztracker.commons.configuration",
        "com.bugztracker.persistence.configuration"},
        excludeFilters = @Filter(type = ANNOTATION, value = Controller.class))
public class WebApplicationContextConfig {

}
