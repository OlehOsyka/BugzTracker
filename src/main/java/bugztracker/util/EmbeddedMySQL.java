package bugztracker.util;

import com.mysql.management.MysqldResource;
import com.mysql.management.MysqldResourceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oleg
 * Date: 29.11.15
 * Time: 19:35
 */
public class EmbeddedMySQL {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedMySQL.class);

    private MysqldResource mysqlDb;
    private int port;
    private String user;
    private String password;

    public EmbeddedMySQL(int port, String user, String password) {
        this.port = port;
        this.user = user;
        this.password = password;
    }

    private void initDb() {
        File databaseDir = new File(System.getProperty("user.home"), "db");

        mysqlDb = new MysqldResource(databaseDir);
        Map database_options = new HashMap();
        database_options.put(MysqldResourceI.PORT, port);
        database_options.put(MysqldResourceI.INITIALIZE_USER, "true");
        database_options.put(MysqldResourceI.INITIALIZE_USER_NAME, user);
        database_options.put(MysqldResourceI.INITIALIZE_PASSWORD, password);

        mysqlDb.start("embedded-mysqld-thread", database_options);
        if (!mysqlDb.isRunning()) {
            throw new RuntimeException("MySQL did not start.");
        }
        LOGGER.info("MySQL is running.");
    }

    public void shutdownDB() {
        mysqlDb.shutdown();
        LOGGER.info("MySQL was shutdown.");
    }
}
