package bugztracker.configuration;

import bugztracker.util.EmbeddedMySQL;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Created by oleg
 * Date: 28.11.15
 * Time: 23:09
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfiguration {

    private static final String BASE_ENTITY_PACKAGE = "bugztracker.entity";

    @Value("${database.embedded}")
    private boolean embedded;

    // DataSource
    @Value("${jdbc.driverclassname}")
    private String className;
    @Value("${jdbc.databaseurl}")
    private String url;
    @Value("${jdbc.username}")
    private String user;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.minidle}")
    private int minIdle;
    @Value("${jdbc.idletimeout}")
    private int idleTimeout;
    @Value("${jdbc.maxpoolsize}")
    private int maxPoolSize;

    // HibernateProperties
    @Value("${jdbc.provider}")
    private String provider;
    @Value("${jdbc.dialect}")
    private String dialect;
    @Value("${database.show_sql}")
    private String showSQL;
    @Value("${current_session_context_class}")
    private String sessionContext;

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws ClassNotFoundException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
        sessionFactory.setPackagesToScan(BASE_ENTITY_PACKAGE);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws ClassNotFoundException {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

    private DataSource dataSource() throws ClassNotFoundException {
        if (embedded) {
            initEmbeddedMySqlDB();
        }
        final HikariDataSource ds = new HikariDataSource();
        ds.setDataSourceClassName(className);
        ds.addDataSourceProperty("url", url);
        ds.addDataSourceProperty("user", user);
        ds.addDataSourceProperty("password", password);
        ds.setMinimumIdle(minIdle);
        ds.setMaximumPoolSize(maxPoolSize);
        ds.setIdleTimeout(idleTimeout);
        return ds;
    }

    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "create-drop");
                setProperty("hibernate.hbm2ddl.import_files", "dump.sql");
                setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
                setProperty("hibernate.dialect", dialect);
                setProperty("hibernate.show_sql", showSQL);
                setProperty("current_session_context_class", sessionContext);
            }
        };
    }

    @Bean(initMethod = "initDb", destroyMethod = "shutdownDB")
    public EmbeddedMySQL initEmbeddedMySqlDB() {
        Pattern p = Pattern.compile(":(\\d+)");
        Matcher m = p.matcher(url);
        if (m.find()) {
            return new EmbeddedMySQL(Integer.parseInt(m.group(1)), user, password);
        } else {
            throw new RuntimeException(format("Can't init embedded MySql. No port for inputted url %s.", url));
        }
    }

}
