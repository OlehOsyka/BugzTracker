package bugztracker.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseConfigurer;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Yuliia Vovk
 * 27.11.15
 */
@Primary
public class H2FileEmbeddedConfiguration implements EmbeddedDatabaseConfigurer {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public void configureConnectionProperties(ConnectionProperties properties, String databaseName) {
        properties.setDriverClass(this.driverClass);
        properties.setUrl("jdbc:h2:file:~/bugztracker.db;INIT=create schema if not exists bugztracker\\;runscript from '~/schema.sql;DATABASE_TO_UPPER=false;");
        properties.setUsername("sa");
        properties.setPassword("");
    }

    private static H2FileEmbeddedConfiguration instance;

    private final Class<? extends Driver> driverClass;


    /**
     * Get the singleton {@code H2EmbeddedDatabaseConfigurer} instance.
     * @return the configurer
     * @throws ClassNotFoundException if H2 is not on the classpath
     */
    @SuppressWarnings("unchecked")
    public static synchronized H2FileEmbeddedConfiguration getInstance() throws ClassNotFoundException {
        if (instance == null) {
            instance = new H2FileEmbeddedConfiguration( (Class<? extends Driver>)
                    ClassUtils.forName("org.h2.Driver", H2FileEmbeddedConfiguration.class.getClassLoader()));
        }
        return instance;
    }


    private H2FileEmbeddedConfiguration(Class<? extends Driver> driverClass) {
        this.driverClass = driverClass;
    }

    @Override
    public void shutdown(DataSource dataSource, String databaseName) {
        try {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute("SHUTDOWN");
        }
        catch (SQLException ex) {
            if (logger.isWarnEnabled()) {
                logger.warn("Could not shutdown embedded database", ex);
            }
        }
    }
}
