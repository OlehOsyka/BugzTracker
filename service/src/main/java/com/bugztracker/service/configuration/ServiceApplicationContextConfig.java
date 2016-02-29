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

    @Bean
    public static PropertySourcesPlaceholderConfigurer initPropertiesConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("mail.properties"),
                new ClassPathResource("service.properties"));
        return configurer;
    }

    @Bean
    public JavaMailSenderImpl provideMailSender(@Value("${mail.host}") String host,
                                                @Value("${mail.sender}") String userName,
                                                @Value("${mail.sender.password}") String password,
                                                @Value("${mail.port}") int port,
                                                @Value("${mail.debug}") String debug,
                                                @Value("${mail.auth}") String auth,
                                                @Value("${mail.starttls.enable}") String starttlsEnable,
                                                @Value("${mail.charset}") String charset,
                                                @Value("${mail.protocol}") String protocol) {
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
