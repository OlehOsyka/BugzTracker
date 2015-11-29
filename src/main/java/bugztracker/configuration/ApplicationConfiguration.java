package bugztracker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by oleg
 * Date: 29.11.15
 * Time: 12:54
 */
@Configuration
public class ApplicationConfiguration {

    @Bean(name = "validator")
    public LocalValidatorFactoryBean initValidator() {
        return new LocalValidatorFactoryBean();
    }
}
