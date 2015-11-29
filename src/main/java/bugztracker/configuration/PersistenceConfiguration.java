package bugztracker.configuration;

import com.mysql.management.MysqldResource;
import com.mysql.management.MysqldResourceI;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by oleg
 * Date: 28.11.15
 * Time: 23:09
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfiguration {

    private final Logger logger = getLogger(getClass());

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
//                setProperty("hibernate.connection.provider_class", provider);
                setProperty("hibernate.dialect", dialect);
                setProperty("hibernate.show_sql", showSQL);
                setProperty("current_session_context_class", sessionContext);
            }
        };
    }

    private void initEmbeddedMySqlDB() throws ClassNotFoundException {
        File databaseDir = new File(System.getProperty("user.home"), "db");
        Pattern p = Pattern.compile(":(\\d+)");
        Matcher m = p.matcher(url);
        if (m.find()) {
            startDatabase(databaseDir, m.group(1), user, password);
        } else {
            throw new RuntimeException(format("Can't init embedded MySql. No port for inputted url %s.", url));
        }
    }

    private void startDatabase(File databaseDir, String port, String userName, String password) {
        MysqldResource mysqldResource = new MysqldResource(databaseDir);
        Map database_options = new HashMap();
        database_options.put(MysqldResourceI.PORT, port);
        database_options.put(MysqldResourceI.INITIALIZE_USER, "true");
        database_options.put(MysqldResourceI.INITIALIZE_USER_NAME, userName);
        database_options.put(MysqldResourceI.INITIALIZE_PASSWORD, password);

        mysqldResource.start("embedded-mysqld-thread", database_options);
        if (!mysqldResource.isRunning()) {
            throw new RuntimeException("MySQL did not start.");
        }
        logger.info("MySQL is running.");
//        how should we shutdown???
//        mysqldResource.shutdown();
    }

}
