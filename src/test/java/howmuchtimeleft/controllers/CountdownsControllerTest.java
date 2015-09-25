package howmuchtimeleft.controllers;

import com.google.common.collect.ImmutableMap;
import com.squareup.okhttp.*;
import howmuchtimeleft.TestHelpers;
import howmuchtimeleft.models.Countdown;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CountdownsControllerTest {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Test
    public void testCreate() throws IOException {
        String requestJSON = TestHelpers.getGson().toJson(
                ImmutableMap.<String, Object>of(
                        "name", "TestCountdown",
                        "targetDateTime", "2015-09-23T20:10:12+00:00"
                )
        );
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, requestJSON);
        Request request = new Request.Builder().url("http://localhost:4567/countdowns").post(body).build();
        Response response = client.newCall(request).execute();
        Countdown countdown = TestHelpers.getGson().fromJson(response.body().string(), Countdown.class);
        assertEquals("TestCountdown", countdown.getName());
        assertNotNull(countdown.getId());
    }
}
