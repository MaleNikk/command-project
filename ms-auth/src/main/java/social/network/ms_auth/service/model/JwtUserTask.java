package social.network.ms_auth.service.model;

import org.springframework.lang.NonNull;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public record JwtUserTask(UUID id, UUID userId, Instant start, Instant finish, Instant upload, String task, Duration timeRelease) {

    @NonNull
    @Override
    public String toString() {
        return String.format("UserTasks:\n{\n\t id: %s,\n\tuserId: %s,\n\tstart: %s,\n\tfinish: %s,\n\tupload: %s,\n\ttask: %s,\n\ttimeRelease: %s\n}",
                id(), userId(), start(), finish(), upload(), task(), timeRelease());
    }
}