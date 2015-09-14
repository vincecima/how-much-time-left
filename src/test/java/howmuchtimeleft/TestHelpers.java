package howmuchtimeleft;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariDataSource;
import jodd.io.StreamUtil;
import jodd.util.ClassLoaderUtil;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestHelpers {
    private static final Config config = ConfigFactory.load();
    private static DataSource dataSource = null;

    public static DataSource getDataSource() {
        if(dataSource == null) {
            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl(config.getString("database.url"));
            ds.setUsername(config.getString("database.username"));
            ds.setPassword(config.getString("database.password"));
            Flyway flyway = new Flyway();
            flyway.setDataSource(ds);
            flyway.migrate();
            dataSource = ds;
        }
        return dataSource;
    }

    public static void loadSQLFixture(Connection conn, String name) throws IOException, SQLException {
        InputStream is = ClassLoaderUtil.getResourceAsStream("/fixtures/insert_countdowns.sql");
        String fixture = new String(StreamUtil.readAvailableBytes(is));
        String[] sqlStatements = fixture.split(";");
        Statement fixtureStatement = conn.createStatement();
        for(String singleSQLStatment : sqlStatements) {
            fixtureStatement.addBatch(singleSQLStatment);
        }
        fixtureStatement.executeBatch();
    }
}
