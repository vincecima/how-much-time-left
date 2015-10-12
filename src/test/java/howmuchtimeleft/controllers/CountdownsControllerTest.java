package howmuchtimeleft.controllers;

import com.google.common.collect.ImmutableMap;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import howmuchtimeleft.TestHelpers;
import howmuchtimeleft.models.Countdown;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CountdownsControllerTest {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Test
    public void testGetNotFound() throws IOException {
        Request request = new Request.Builder().url("http://localhost:4567/countdowns/6ae5c022-8093-4e9b-82e3-b64ad50ac68b").get().build();
        Response response = TestHelpers.getHttpClient().newCall(request).execute();
        assertEquals(404, response.code());
    }

    @Test
    public void testGet() throws SQLException, IOException {
        try(java.sql.Connection conn = TestHelpers.getDataSource().getConnection()) {
            TestHelpers.loadSQLFixture(conn, "insert_countdowns");
        }
        Request request = new Request.Builder().url("http://localhost:4567/countdowns/d0d0fa98-151f-4de6-b971-8c65de2d12f0").get().build();
        Response response = TestHelpers.getHttpClient().newCall(request).execute();
        Countdown countdown = TestHelpers.getGson().fromJson(response.body().string(), Countdown.class);
        assertEquals("test", countdown.getName());
        assertEquals(200, response.code());
    }

    @Test
    public void testCreate() throws IOException {
        String requestJSON = TestHelpers.getGson().toJson(
            ImmutableMap.<String, Object>of(
                "name", "TestCountdown",
                "targetDateTime", "2015-09-23T20:10:12+00:00"
            )
        );
        RequestBody body = RequestBody.create(JSON, requestJSON);
        Request request = new Request.Builder().url("http://localhost:4567/countdowns").post(body).build();
        Response response = TestHelpers.getHttpClient().newCall(request).execute();
        Countdown countdown = TestHelpers.getGson().fromJson(response.body().string(), Countdown.class);
        assertEquals("TestCountdown", countdown.getName());
        assertNotNull(countdown.getId());
    }
}
