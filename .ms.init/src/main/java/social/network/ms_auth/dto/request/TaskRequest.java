package social.network.ms_auth.dto.request;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public record TaskRequest(UUID userId, String task, Duration timeRelease, Instant start) {
}