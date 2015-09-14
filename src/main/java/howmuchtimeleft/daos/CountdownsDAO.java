package howmuchtimeleft.daos;

import howmuchtimeleft.models.Countdown;
import jodd.db.DbQuery;
import org.joda.time.DateTime;

import java.sql.Connection;
import java.util.UUID;

public class CountdownsDAO {
    private static final String FIND_SQL = "SELECT id, name, target_date_time as targetDateTime FROM countdowns WHERE id = :id";
    private static final String CREATE_SQL = "INSERT INTO countdowns(id, name, target_date_time) VALUES (DEFAULT, :cd.name, :cd.targetDateTime) RETURNING id";

    public Countdown find(Connection conn, UUID id) {
        DbQuery query = new DbQuery(conn, FIND_SQL);
        query.setObject("id", id);
        return query.find(resultSet -> {
            return new Countdown((UUID) resultSet.getObject("id"), resultSet.getString("name"), new DateTime(resultSet.getTimestamp("targetDateTime").getTime()));
        });
    }
}
