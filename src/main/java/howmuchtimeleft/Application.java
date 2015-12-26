package howmuchtimeleft;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariDataSource;
import howmuchtimeleft.controllers.CountdownsController;
import howmuchtimeleft.utils.*;
import org.flywaydb.core.Flyway;
import org.joda.time.DateTime;

import javax.sql.DataSource;

public class Application {
    private Config config = null;
    private DataSource dataSource = null;
    private Gson gson = null;

    public static void main(String[] args) {
        Config config = ConfigFactory.load();
        new Application(config).start();
    }

    public Application(Config config) {
        this.config = config;
        this.createDataSource();
        this.runMigrations();
        this.createGson();
        this.setupExceptionHandling();
    }

    public void start() {
        new CountdownsController(this.dataSource, this.gson).use();
    }

    private void createDataSource() {
        if(this.dataSource == null) {
            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl(config.getString("database.url"));
            ds.setUsername(config.getString("database.username"));
            ds.setPassword(config.getString("database.password"));
            this.dataSource = ds;
        }
    }

    private void runMigrations() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }

    private void createGson() {
        if(this.gson == null) {
            this.gson = new GsonBuilder().
                registerTypeAdapter(DateTime.class, new DateTimeDeserializer()).
                registerTypeAdapter(DateTime.class, new DateTimeSerializer()).
                create();
        }
    }

    private void setupExceptionHandling() {
        new ErrorHandler(this.gson).use();
    }
}
