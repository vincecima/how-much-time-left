package howmuchtimeleft;

import com.google.gson.*;
import howmuchtimeleft.models.Countdown;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.UUID;

public class CountdownDeserializer implements JsonDeserializer<Countdown> {
    @Override
    public Countdown deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        return new Countdown(
            UUID.fromString(jo.get("id").getAsString()),
            jo.get("name").getAsString(),
            context.deserialize(jo.get("targetDateTime"), DateTime.class)
        );
    }
}
