package bugztracker.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by oleg
 * Date: 29.11.15
 * Time: 16:59
 */
@SpringBootApplication
@Configuration
@EnableScheduling
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Application{

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
