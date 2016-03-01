package com.bugztracker.service.configuration;

import com.bugztracker.service.impl.RegistrationPassScheduler;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import java.util.Properties;

/**
 * Author: Yuliia Vovk
 * Date: 20.02.16
 * Time: 10:27
 */
@Configuration
@ComponentScan("com.bugztracker.service")
public class ServiceApplicationContextConfig {

    @Bean(name = "serviceProperties")
    public static PropertySourcesPlaceholderConfigurer initPropertiesConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setPlaceholderPrefix("${service.");
        configurer.setLocations(
                new ClassPathResource("mail.properties"),
                new ClassPathResource("service.properties"));
        return configurer;
    }

    @Bean
    public JavaMailSenderImpl provideMailSender(@Value("${service.mail.host}") String host,
                                                @Value("${service.mail.sender}") String userName,
                                                @Value("${service.mail.sender.password}") String password,
                                                @Value("${service.mail.port}") int port,
                                                @Value("${service.mail.debug}") String debug,
                                                @Value("${service.mail.auth}") String auth,
                                                @Value("${service.mail.starttls.enable}") String starttlsEnable,
                                                @Value("${service.mail.charset}") String charset,
                                                @Value("${service.mail.protocol}") String protocol) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(userName);
        mailSender.setPassword(password);

        Properties properties = new Properties();
        properties.setProperty("mail.debug", debug);
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.starttls.enable", starttlsEnable);
        properties.setProperty("mail.mime.charset", charset);
        properties.setProperty("mail.transport.protocol", protocol);

        mailSender.setJavaMailProperties(properties);
        return mailSender;

    }

    @Bean
    public VelocityEngineFactoryBean provideVelocityEngine() {
        VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngineFactoryBean.setVelocityProperties(properties);
        return velocityEngineFactoryBean;
    }

    @Bean(autowire = Autowire.BY_NAME)
    public RegistrationPassScheduler provideRegistrationScheduler() {
        return new RegistrationPassScheduler();
    }

}
