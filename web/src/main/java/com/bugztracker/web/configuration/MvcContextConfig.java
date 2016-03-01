package com.bugztracker.web.configuration;

import org.joda.time.Period;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Author: Yuliia Vovk
 * Date: 21.02.16
 * Time: 10:10
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.bugztracker.web.controllers")
public class MvcContextConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver provideViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").
                addResourceLocations("/resources/").
                setCachePeriod(Period.years(1).getSeconds());
    }

}
