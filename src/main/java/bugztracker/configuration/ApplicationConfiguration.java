package bugztracker.configuration;

import bugztracker.service.impl.Scheduler;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Properties;

/**
 * Created by oleg
 * Date: 29.11.15
 * Time: 12:54
 */
@Configuration
@ComponentScan(basePackages = {"bugztracker"},
        excludeFilters = {@Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class ApplicationConfiguration {

    @Bean(name = "validator")
    public LocalValidatorFactoryBean initValidator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer initPropertiesConfig() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(new ClassPathResource("project.properties"),
                new ClassPathResource("jdbc.properties"),
                new ClassPathResource("mail.properties"),
                new ClassPathResource("connection.properties"));
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }


    @Bean(name = "mailSender")
    public JavaMailSender initMailSender(@Value("${mail.host}") String host,
                                         @Value("${mail.port}") int port,
                                         @Value("${mail.sender}") String user,
                                         @Value("${mail.sender.password}") String password,
                                         @Value("${mail.debug}") final String debug,
                                         @Value("${mail.auth}") final String auth,
                                         @Value("${mail.starttls.enable}") final String startTLS,
                                         @Value("${mail.charset}") final String charset,
                                         @Value("${mail.protocol}") final String protocol) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(user);
        mailSender.setPassword(password);
        mailSender.setJavaMailProperties(new Properties() {
            {
                setProperty("mail.debug", debug);
                setProperty("mail.smtp.auth", auth);
                setProperty("mail.smtp.starttls.enable", startTLS);
                setProperty("mail.mime.charset", charset);
                setProperty("mail.transport.protocol", protocol);
            }
        });
        return mailSender;
    }

    @Bean(name = "velocityEngine")
    public VelocityEngineFactoryBean initTemplate() {
        VelocityEngineFactoryBean factoryBean = new VelocityEngineFactoryBean();
        factoryBean.setVelocityProperties(new Properties() {{
            setProperty("resource.loader", "class");
            setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        }});
        return factoryBean;
    }

    @Bean(name = "scheduler", autowire = Autowire.BY_NAME)
    public Scheduler initSchedulerService() {
        return new Scheduler();
    }

}
