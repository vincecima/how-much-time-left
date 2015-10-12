package howmuchtimeleft.controllers;

import com.google.gson.Gson;
import howmuchtimeleft.daos.CountdownsDAO;
import howmuchtimeleft.models.Countdown;
import howmuchtimeleft.models.CountdownValidator;
import jodd.vtor.Violation;
import spark.Request;
import spark.Response;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CountdownsController {
    private DataSource dataSource = null;
    private Gson gson = null;

    public CountdownsController(DataSource dataSource, Gson gson) {
        this.dataSource = dataSource;
        this.gson = gson;
    }

    public void use() {
        spark.Spark.get("/countdowns/:id", this::get);
        spark.Spark.post("/countdowns", this::create);
    }

    private String get(Request request, Response response) throws SQLException {
        Countdown countdown = null;
        try(Connection conn = this.dataSource.getConnection()) {
            countdown = new CountdownsDAO().find(conn, UUID.fromString(request.params("id")));
        }
        if(countdown != null) {
            response.status(200);
            response.type("application/json");
            return this.gson.toJson(countdown);
        }
        else {
            response.status(404);
            return "";
        }

    }

    private String create(Request request, Response response) throws SQLException {
        Countdown countdown = gson.fromJson(request.body(), Countdown.class);
        CountdownValidator validator = new CountdownValidator();
        List<Violation> errors = validator.validate(countdown);
        if(errors == null) {
            try(Connection conn = this.dataSource.getConnection()) {
                countdown = new CountdownsDAO().create(conn, countdown);
            }
            response.status(201);
            response.type("application/json");
            return this.gson.toJson(countdown);
        }
        else {
            response.status(422);
            return "";
        }
    }
}
