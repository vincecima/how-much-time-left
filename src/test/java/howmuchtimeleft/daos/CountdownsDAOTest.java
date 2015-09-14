package howmuchtimeleft.daos;

import howmuchtimeleft.TestHelpers;
import howmuchtimeleft.models.Countdown;
import org.joda.time.DateTime;
import org.junit.Test;

import java.sql.Connection;
import java.util.UUID;

import static org.junit.Assert.*;

public class CountdownsDAOTest {
    @Test
    public void testFind() throws Exception {
        try(Connection conn = TestHelpers.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            TestHelpers.loadSQLFixture(conn, "insert_countdowns");
            CountdownsDAO dao = new CountdownsDAO();
            UUID exptectedUUID = UUID.fromString("d0d0fa98-151f-4de6-b971-8c65de2d12f0");
            Countdown countdown = dao.find(conn, exptectedUUID);
            assertEquals(exptectedUUID, countdown.getId());
            conn.rollback();
        }
    }

    @Test
    public void testCreate() throws Exception {
        try(Connection conn = TestHelpers.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            CountdownsDAO dao = new CountdownsDAO();
            Countdown expectedCountdown = new Countdown(null, "expected", DateTime.parse("2010-06-30T01:20"));
            expectedCountdown = dao.create(conn, expectedCountdown);
            Countdown foundCountdown = dao.find(conn, expectedCountdown.getId());
            assertEquals(expectedCountdown.getTargetDateTime(), foundCountdown.getTargetDateTime());
            assertEquals(expectedCountdown.getName(), foundCountdown.getName());
            conn.rollback();
        }
    }
}