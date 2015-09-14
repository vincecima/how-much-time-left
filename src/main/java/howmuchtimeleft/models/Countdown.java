package howmuchtimeleft.models;

import org.joda.time.DateTime;

import java.util.UUID;

public class Countdown {
    private final UUID id;
    private final String name;
    private final DateTime targetDateTime;

    public Countdown(UUID id, String name, DateTime targetDateTime) {
        this.id = id;
        this.name = name;
        this.targetDateTime = targetDateTime;
    }

    public UUID getId() { return id; }

    public String getName() {
        return name;
    }

    public DateTime getTargetDateTime() {
        return targetDateTime;
    }
}